package com.chauncy.blog.service.impl;

import com.chauncy.blog.common.message.ParamMap;
import com.chauncy.blog.common.message.MessageSender;
import com.chauncy.blog.common.result.AjaxResult;
import com.chauncy.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageSender messageSender;

    /**
     * 发送短信验证码
     *
     * @return
     */
    @Override
    public AjaxResult sendValidCode(String phoneNum) {

        AjaxResult smsResult = messageSender.send(phoneNum);

        return smsResult;
    }

    /**
     * 获取短信日志
     */
    @Override
    public AjaxResult getMessageLog() {

        AjaxResult logResult = messageSender.getMessageLog();
        System.out.println(logResult.toString());
        
        return logResult;
    }
}
