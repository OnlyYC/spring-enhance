package com.liaoyb.limit.core;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Function: general annotation
 *
 * @author crossoverJie Date: 27/04/2018 15:50
 * @since JDK 1.8
 */
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CommonAspect {

    private static Logger logger = LoggerFactory.getLogger(CommonAspect.class);

    @Autowired
    private Limit limit;

    @Pointcut("@annotation(com.liaoyb.limit.annotation.CommonLimit)")
    private void check() {
    }

    @Before("check()")
    public void before(JoinPoint joinPoint) throws Exception {
        logger.info("限流拦截开始....");
        boolean isLimit = limit.limit();
        if (!isLimit) {
            logger.warn("request has bean limited");
            throw new RuntimeException("request has bean limited");
        }
        logger.info("限流拦截结束....");
    }
}
