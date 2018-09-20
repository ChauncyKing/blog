package com.chauncy.blog.common.message.output;

import java.io.Serializable;

/**
 * 短信发送失败输出结果对象
 *
 * @author Chauncy
 */
public class SmsErrorOutput implements Serializable {


    /**
     * status : error
     * code : XXX
     * msg : Empty signature
     * <p>
     * 结果参数说明：
     * status : 请求状态
     * code : 错误码
     * msg : 错误描述
     */

    private String status;

    private Integer code;

    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
