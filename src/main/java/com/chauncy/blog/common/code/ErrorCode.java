package com.chauncy.blog.common.code;

/**
 * 错误码定义
 *
 * @author Chauncy
 */
public enum ErrorCode{

    /**
     * 系统错误
     */
    ERROR_DEFAULT(999999, "系统未知错误"),


    /**
     * 短息服务异常 1001-2000
     */
    MESSAGE_SERVICE_ERROR(1001,"短信服务异常");

    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 错误描述
     */
    private String description;

    ErrorCode(Integer code, String description) {
        this.errorCode = code;
        this.description = description;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
