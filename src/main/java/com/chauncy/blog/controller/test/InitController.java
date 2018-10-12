package com.chauncy.blog.controller.test;

import com.chauncy.blog.common.code.ErrorCode;
import com.chauncy.blog.common.redis.RedisService;
import com.chauncy.blog.common.result.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RedisService redisService;

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
        return AjaxResult.success();
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

    /**
     * 测试 Redis Server 是否可用
     * 关闭防火墙，配置规则中开启相应端口，启动服务
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/redis")
    public AjaxResult redis() {
        String value = redisService.connectTest();
        return AjaxResult.success(value);
    }


}
