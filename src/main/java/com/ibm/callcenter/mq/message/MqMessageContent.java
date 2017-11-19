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
    private String customerId;
    private String bussinessId;
    private String content;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 发送用户Id
     * @return
     */
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * 等待接收的业务部门
     * @return
     */
    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
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
