package com.chauncy.blog.common.message.output;

public class LogResult {

    /**
     * 发送短信唯一标识 - sendID : 7ac69ff98e48c9b3b0f49afa5a0788c7
     * 应用标识 - project : Gy1CR1
     * 接受人手机号 - recipient : 138xxxxxxxx
     * 内容 - message : 如需重置您的 SUBMAIL 账户密码，请在重置页面输入此验证码：1425 ，并输入新的密码。
     * 签名 - signature : 【SUBMAIL】
     * 发送状态 - result_status : delivered
     * 请求API - api : message/xsend
     * 发送时间 - send_date : 2015-01-19 15:21:27
     * 送达时间 - sent_date : 2015-01-19 15:21:27
     * 内容长度 - length : 56
     * 余额 - credit : 1
     */

    private String sendId;

    private String project;

    private String recipient;

    private String message;

    private String signature;

    private String result_status;

    private String api;

    private String sendDate;

    private String sentDdate;

    private int length;

    private int credit;

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getResult_status() {
        return result_status;
    }

    public void setResult_status(String result_status) {
        this.result_status = result_status;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getSentDdate() {
        return sentDdate;
    }

    public void setSentDdate(String sentDdate) {
        this.sentDdate = sentDdate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}