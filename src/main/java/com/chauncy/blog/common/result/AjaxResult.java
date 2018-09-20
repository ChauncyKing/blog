package com.chauncy.blog.common.result;

import com.alibaba.fastjson.JSONObject;
import com.chauncy.blog.common.code.ErrorCode;

import java.io.Serializable;

/**
 * Controller 统一使用该格式来响应请求
 *
 * @author Chauncy
 */
public class AjaxResult implements Serializable {

    /**
     * 请求成功：响应码
     */
    private static final Integer SUCCESS_CODE = 200;

    /**
     * 请求失败：默认响应码（系统错误）
     */
    private static final Integer ERROR_CODE = 99999;

    /**
     * 请求成功：响应描述
     */
    private static final String SUCCESS_DESCRIPTION = "SUCCESS";

    /**
     * 请求失败：响应描述
     */
    private static final String ERROR_DESCRIPTION = "ERROR";

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应描述
     */
    private String description;

    /**
     * 响应结果
     */
    private Object data;


    public AjaxResult(Integer code, String description, Object data) {
        this.code = code;
        this.description = description;
        this.data = data;
    }

    public AjaxResult(Integer code, String description) {
        this.code = code;
        this.description = description;
        this.data = new Object();
    }

    /**
     * 请求成功，无数据返回
     *
     * @return
     */
    public static AjaxResult success() {
        return new AjaxResult(SUCCESS_CODE, SUCCESS_DESCRIPTION);
    }

    /**
     * 请求成功，有数据返回
     *
     * @return
     */
    public static AjaxResult success(Object data) {
        return new AjaxResult(SUCCESS_CODE, SUCCESS_DESCRIPTION, data);
    }

    /**
     * 请求失败(系统错误)，无数据返回
     *
     * @return
     */
    public static AjaxResult error() {
        return new AjaxResult(ERROR_CODE, ERROR_DESCRIPTION);
    }

    /**
     * 请求失败
     *
     * @return
     */
    public static AjaxResult error(Integer errorCode, String errorMessage) {
        return new AjaxResult(errorCode, errorMessage);
    }

    /**
     * 通过枚举错误码返回
     *
     * @param errorCodeEnum
     * @return
     */
    public static AjaxResult error(ErrorCode errorCodeEnum) {
        return new AjaxResult(errorCodeEnum.getErrorCode(), errorCodeEnum.getDescription());
    }

    /**
     * 请求是否成功
     * ### 方法名一定不要用 is 开头，否则会被识别成 成员变量返回 ###
     * @return
     */
    public boolean excuteSuccess() {
        if (SUCCESS_CODE.equals(this.getCode())) {
            return true;
        }
        return false;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
