package com.chauncy.blog.component;

import com.chauncy.blog.common.redis.RedisService;
import com.chauncy.blog.common.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Slf4j
public class HttpResolverInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求的 IP
        String ip = request.getRemoteAddr();
        // 获取请求的访问路径
        String url = RequestUtil.getRealUrl(request);
        // todo 请求归属地（invoke 工具类）
        // 触发 Session 的创建
        HttpSession session = request.getSession();
        log.info("[请求IP:{}]", ip);
        log.info("[请求URL:{}]", url);

        // todo : 持久化 ：记录用户的请求日志
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
