> 在做搜索服务时，当业务方数据源改变时，需要改变搜索引擎中索引的数据。可以`定时拉取`也可以`实时推送`。为了实现同步更新，选择了实时推送。实时推送也有两种方式，一种是提供索引更新接口供业务方调用，在接口中将变化的数据更新到搜索引擎中；另一种是使用消息队列，业务方在数据改变时，将改变的数据插入队列，服务端的消费者实时监听队列，并进行索引更新。考虑到使用消息队列有三点好处，选择了第二种方式。`使用消息队列的好处`: 一，使用消息队列可以异步处理更新操作降低接口响应时间；二，对于消费端由于不可预测原因导致消息无法处理时，数据可以暂存在队列中，等消费者服务恢复后可以继续处理历史数据,提高可用性；三，将业务方的服务和索引更新服务解耦。下面简单封装了一个spring-rabbit的maven模块，第一是能够作为例子作为参考，第二是能够快速的进行复用。

参考了下面几篇博客：   
http://syntx.io/getting-started-with-rabbitmq-using-the-spring-framework/     
https://blog.codecentric.de/en/2011/04/amqp-messaging-with-rabbitmq/    
http://wb284551926.iteye.com/blog/2212869  

<!-- more -->
## 本地安装rabbitmq
只介绍centos6.5 系统下如何安装
### 安装编译工具
```
yum -y install make gcc gcc-c++ kernel-devel m4 ncurses-devel openssl-devel
```
### 安装Erlang工具
- 下载erlang

下载地址：http://download.csdn.net/detail/a15134566493/9517595  
官方下载地址：http://erlang.org/download/otp_src_18.3.tar.gz
  
- 安装

```
#解压
tar xvf otp_src_18.3.tar.gz
cd otp_src_18.3

#配置 '--prefix'指定的安装目录
./configure --prefix=/usr/local/erlang --with-ssl -enable-threads -enable-smmp-support -enable-kernel-poll --enable-hipe --without-javac

#安装
make && make install
```
- 配置erlang环境变量

```
vim /etc/profile

#在文件末尾添加下面代码 'ERLANG_HOME'等于上一步'--prefix'指定的目录
ERLANG_HOME=/usr/local/erlang
PATH=$ERLANG_HOME/bin:$PATH
export ERLANG_HOME
export PATH

#使环境变量生效
source /etc/profile

#输入命令检验是否安装成功
erl
#如果进入erl命令行模式则代表成功
```
### 安装RabbitMQ

- 下载RabbitMQ  

官方下载地址http://www.rabbitmq.com/releases/rabbitmq-server/v3.6.1/rabbitmq-server-generic-unix-3.6.1.tar.xz

- 安装 

RabbitMQ3.6版本无需make、make install 解压就可以用

```
#解压rabbitmq，官方给的包是xz压缩包，所以需要使用xz命令
xz -d rabbitmq-server-generic-unix-3.6.1.tar.xz

#xz解压后得到.tar包，再用tar命令解压
tar -xvf rabbitmq-server-generic-unix-3.6.1.tar

#移动目录 看个人喜好
cp -rf ./rabbitmq_server-3.6.1 /usr/local/
cd /usr/local/

#修改文件夹名
mv rabbitmq_server-3.6.1 rabbitmq-3.6.1

#开启管理页面插件
cd ./rabbitmq-3.6.1/sbin/
./rabbitmq-plugins enable rabbitmq_management
```

- 可以禁用用不着的插件

```
 rabbitmq-plugins disable --offline rabbitmq_stomp
 rabbitmq-plugins disable --offline rabbitmq_mqtt
```

- 启动rabbitmq

```
#启动命令，该命令ctrl+c后会关闭服务
./rabbitmq-server

#在后台启动Rabbit
./rabbitmq-server -detached

#关闭服务
./rabbitmqctl stop

#关闭服务(kill) 找到rabbitmq服务的pid   [不推荐]
ps -ef|grep rabbitmq
kill -9 ****
```
RabbitMQ默认启动端口是5672

- 添加管理员账号

```
#进入RabbitMQ安装目录
cd /usr/local/rabbitmq-3.6.1/sbin

#添加用户
#rabbitmqctl add_user Username Password
./rabbitmqctl add_user rabbitadmin 123456

#分配用户标签
#rabbitmqctl set_user_tags User Tag
#[administrator]:管理员标签
./rabbitmqctl set_user_tags rabbitadmin administrator
```
- 登录管理界面

web端口号默认是15672

```
浏览器输入地址：http://服务器IP地址:15672/
```

## 测试与使用

### 测试
需要在test.properties中修改以下参数
```
mq.host=localhost
mq.username=rabbitadmin
mq.password=123456
mq.port=5672
mq.vhost=/
mq.default.routingkey=rabbit_queue_default
mq.default.queuename=rabbit_queue_default
```
在`src/test`下提供了单元测试。

### 说明

队列消费方法支持主动获取和订阅模式，订阅模式的监听器在AMQPConfig中提供了配置样例：
```
@Bean
SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
	SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	container.setConnectionFactory(connectionFactory);
	container.setQueueNames(mqDefaultQueueName);
	container.setMessageListener(new MyMessageListener());
	return container;
}
```
`MyMessageListener`的内容如下：
```
import com.ibm.callcenter.mq.message.MqMessageContent;
import com.ibm.callcenter.mq.utils.Transform;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MyMessageListener implements MessageListener{
    @Override
    public void onMessage(Message message) {
        System.out.println("监听器");
        MqMessageContent mqMessageContent=(MqMessageContent) Transform.ByteToObject(message.getBody());
        System.out.println(mqMessageContent.getBusinessKey());
    }
}
```

### 使用

因为配置成了maven的model，所以其他的项目可以快速的添加到依赖中。  
在自己的项目`pon.xml`中添加
```
<dependency>
  <groupId>com.ibm</groupId>
  <artifactId>ibm-server-rabbit</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>

```
建议使用时，修改下本项目的`pom.xml`，因为和springboot的整合，所以建议将其中依赖的spring版本修改为和自己的项目一致。  
本项目中使用的是`1.5.3.RELEASE`版本
```
<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>1.5.3.RELEASE</version>
</parent>
```