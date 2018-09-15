package com.chauncy.blog.controller.test;

import com.chauncy.blog.common.code.ErrorCode;
import com.chauncy.blog.common.result.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 初始化测试
 *
 * @author Chauncy
 */
@Controller
@RequestMapping("/test")
public class InitController {

    /**
     * 测试项目是否成功初始化
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/init")
    public String init() {
        return "项目初始化成功";
    }

    /**
     * 测试返回成功结果 AjaxResult
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/success")
    public AjaxResult success() {
        return AjaxResult.success("success");
    }

    /**
     * 测试返回失败结果 AjaxResult
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/error")
    public AjaxResult error() {
        return AjaxResult.error(ErrorCode.ERROR_DEFAULT);
    }
}
