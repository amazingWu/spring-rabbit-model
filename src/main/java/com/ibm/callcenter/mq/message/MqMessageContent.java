package com.ibm.callcenter.mq.message;

import com.ibm.callcenter.mq.utils.Transform;

import java.io.Serializable;
import java.util.Date;

/**
 * Mq客服发送到业务部门消息对象
 */
public class MqMessageContent implements Serializable{
	private static final long serialVersionUID = 1L;
	private Date createTime;
    private String customerKey; // 用户的Key
    private String businessKey; // 业务线的key
    private String content;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 发送用户key
     * @return
     */
    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    /**
     * 等待接收的业务部门key
     * @return
     */
    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    /**
     * 消息内容
     * @return
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 转换成字节数组
     * @return
     */
    public byte[] getBytes(){
        return Transform.ObjectToByte(this);
    }

}
