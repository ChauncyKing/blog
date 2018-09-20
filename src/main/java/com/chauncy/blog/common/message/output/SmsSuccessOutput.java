package com.chauncy.blog.common.message.output;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 短信发送成功输出结果对象
 *
 * @author Chauncy
 */
public class SmsSuccessOutput implements Serializable {

    /**
     * status : success
     * send_id : 561d52e50d891dc844332e599ffa5f8b
     * fee : 1
     * sms_credits : 39
     * transactional_sms_credits : 0
     * <p>
     * 结果参数说明：
     * status : 请求状态
     * send_id 短信发送的ID (全局唯一)
     * fee 计费条数（发送正文按字数计费）
     * sms_credits：运营类短信余额
     * transactional_sms_credits 事务短信余额
     */

    private String status;

    private String sendId;

    private Integer fee;

    private Integer smsCredits;

    private Integer transactionalSmsCredits;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Integer getSmsCredits() {
        return smsCredits;
    }

    public void setSmsCredits(Integer smsCredits) {
        this.smsCredits = smsCredits;
    }

    public Integer getTransactionalSmsCredits() {
        return transactionalSmsCredits;
    }

    public void setTransactionalSmsCredits(Integer transactionalSmsCredits) {
        this.transactionalSmsCredits = transactionalSmsCredits;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
