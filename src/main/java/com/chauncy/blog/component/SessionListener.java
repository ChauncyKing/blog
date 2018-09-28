package com.chauncy.blog.component;

import com.chauncy.blog.common.constant.ComponentConsts;
import com.chauncy.blog.common.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Session 监听器
 */
@Slf4j
public class SessionListener implements HttpSessionListener, ServletContextListener {

    private RedisService redisService;

    /**
     * 在 Session 创建时被调用，Session 的创建是通过 Request 触发的
     *
     * @param httpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        // 设置 Session 的过期时间（单位 : 秒）
        session.setMaxInactiveInterval(60 * 10);
        log.info("[Session监听器][Session Created……][SessionID:{}]", session.getId());
        // 更新访问人数
        Long visitNum = redisService.incr(ComponentConsts.VISIT_NUM);
        // 更新在线人数
        Long onlineNum = redisService.incr(ComponentConsts.ONLINE_NUM);
        // 该用户的上线时间
        redisService.hset(ComponentConsts.START_TIME, session.getId(), System.currentTimeMillis());
        // 打印访问人数
        log.info("[访问人数:{}]", visitNum);
        log.info("[在线人数:{}]", onlineNum);
        // todo : 持久化 ，保存用户的上线时间（插入）
    }

    /**
     * 在 Session 销毁时被调用
     *
     * @param httpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        // 1.获取 SessionID
        String sessionId = httpSessionEvent.getSession().getId();
        // 2.下线时间 = 当前时间 - Session 失效时间（失效时间修改为：10分钟）
        long now = System.currentTimeMillis();
        long endTime = now - (1000L * 10);
        // 更新在线人数
        redisService.decr(ComponentConsts.ONLINE_NUM);

        log.info("[Session监听器][Session Destroyed……][SessionID:{}]", session.getId());

        // 计算在线时长
        Long startTime = redisService.get(ComponentConsts.START_TIME, Long.class);
        Long onlineTime = endTime - startTime;
        redisService.hset(ComponentConsts.ONLINE_NUM, sessionId, onlineTime);

        // todo : 持久化 ，保存用户的下线时间，在线时长（插入）
    }

    /**
     * 获取 Spring 的应用上下文 , 因为无法通过注入的方式获取 Spring 容器管理的 Bean
     *
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // 获取 Spring 的应用上下文
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        // 获取被 Spring 管理的 Bean
        RedisService redisService = (RedisService) applicationContext.getBean("redisService");
        // 赋值给 成员变量
        this.redisService = redisService;
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
