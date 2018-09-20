package com.chauncy.blog.controller;

import com.chauncy.blog.common.message.MessageSender;
import com.chauncy.blog.common.result.AjaxResult;
import com.chauncy.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信
 *
 * @author Chauncy
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 给指定用户发送短信验证码
     */
    @RequestMapping(value = "/send/{phoneNum}")
    public AjaxResult sendMessage(@PathVariable("phoneNum") String phoneNum) {

        return messageService.sendValidCode(phoneNum);
    }

    /**
     * 获取短信日志
     */
    @RequestMapping(value = "/log")
    public AjaxResult getMessageLog() {

        return messageService.getMessageLog();
    }
}
