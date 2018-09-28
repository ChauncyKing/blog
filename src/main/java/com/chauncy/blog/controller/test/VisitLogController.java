package com.chauncy.blog.controller.test;

import com.chauncy.blog.common.constant.ComponentConsts;
import com.chauncy.blog.common.redis.RedisService;
import com.chauncy.blog.common.result.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visit/log")
public class VisitLogController {

    @Autowired
    private RedisService redisService;

    /**
     * 获取在线人数
     *
     * @return
     */
    @GetMapping("/onlineNum")
    public AjaxResult getOnlineNum() {
        Long onlineNum = redisService.get(ComponentConsts.ONLINE_NUM, Long.class);
        return AjaxResult.success(onlineNum);
    }

    /**
     * 获取访问人数
     *
     * @return
     */
    @GetMapping("/visitNum")
    public AjaxResult getVisitNum() {
        Long visitNum = redisService.get(ComponentConsts.VISIT_NUM, Long.class);
        return AjaxResult.success(visitNum);
    }

    /**
     * 清空在线人数和访问人数
     *
     * @return
     */
    @GetMapping("/ac")
    public AjaxResult Ac() {
        redisService.set(ComponentConsts.ONLINE_NUM, 0);
        redisService.set(ComponentConsts.VISIT_NUM, 0);
        return AjaxResult.success();
    }

}
