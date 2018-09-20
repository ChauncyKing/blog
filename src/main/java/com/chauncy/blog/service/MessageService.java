package com.chauncy.blog.service;

import com.chauncy.blog.common.result.AjaxResult;
import org.springframework.stereotype.Service;

/**
 * 短信服务类
 *
 * @author Chauncy
 */
public interface MessageService {

    /**
     * 发送验证码
     *
     * @return
     */
    AjaxResult sendValidCode(String phoneNum);

    AjaxResult getMessageLog();
}
