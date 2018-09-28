package com.chauncy.blog.common.aspect;

import com.chauncy.blog.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RequestLogAspect {

    /**
     * 配置切入点 ， controller包下的所有方法
     */
    @Pointcut("execution(public * com.chauncy.blog.controller..*(..))")
    public void requestLog() {

    }

    @Before("requestLog()")
    public void before(JoinPoint joinPoint) {
        // 目标方法的所属类名（简单类名）
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        // 目标方法的方法名
        String funName = joinPoint.getSignature().getName();
        // 获取目标方法的参数值 数组
        Object[] args = joinPoint.getArgs();
        // 获取目标方法的参数明 数组
        String[] paramNames = ((CodeSignature) joinPoint.getStaticPart().getSignature()).getParameterNames();

        // 参数名 + 参数值
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            builder.append(paramNames[i] + ":" + args[i] + " , ");
        }
        log.info("===============================================================");
        log.info("[调用方法: {}]", className + " # " + funName);
        // 当参数不为空时
        if (!StringUtil.isNullOrEmpty(builder.toString())) {
            String paramSignature = builder.toString().substring(0, builder.length() - 2);
            log.info("[参数: {}]", paramSignature);
        }
    }

    @AfterReturning(pointcut = "requestLog()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.info("[请求结果: {}]", result);
        log.info("===============================================================");
    }

    @AfterThrowing(pointcut = "requestLog()", throwing = "exception")
    public void afterThrowing(Throwable exception) {
        log.info("[请求异常: {}]", exception.getMessage());
        log.info("===============================================================");
    }

}
