package com.zoo.tiger.me.asp;

import com.zoo.tiger.me.annotation.Log;
import com.zoo.tiger.me.util.ExpressionUtils;
import com.zoo.tiger.me.util.MdcUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author Tiger
 */

@Slf4j
@Aspect
@Component
public class LogAsp {
    private DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

    @Around(value = "@annotation(logAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint, Log logAnnotation) throws Throwable {

        extractLog(joinPoint, logAnnotation);

        Object proceed = joinPoint.proceed();

        MdcUtils.clear();

        return proceed;
    }

    private void extractLog(ProceedingJoinPoint joinPoint, Log logAnnotation) {
        try {
            MdcUtils.setTraceID(UUID.randomUUID().toString());
            MdcUtils.setStartTime(System.currentTimeMillis());
            MdcUtils.setCost(0);
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            String[] paramNames = nameDiscoverer.getParameterNames(method);

            EvaluationContext context = new StandardEvaluationContext();
            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                context.setVariable(paramNames[i], args[i]);
            }

            ExpressionUtils.parseValue2MDC(logAnnotation, context);

        } catch (Exception e) {
            log.error("logAspect异常", e);
        }
    }
}

