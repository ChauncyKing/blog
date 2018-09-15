package com.chauncy.blog.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 初始化测试
 * @author Chauncy
 */
@Controller
@RequestMapping("/test")
public class InitController {

    @ResponseBody
    @RequestMapping(value = "/init")
    public String init(){
        return "项目初始化成功";
    }

}
