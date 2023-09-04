package by.it.medved.aspects;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static by.it.medved.util.Patterns.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;


@Slf4j
@Aspect
@Component
public class ControllerLogAspect {

    @Pointcut("execution(* by.it.medved.controllers..*(..)) && !@annotation(by.it.medved.annotations.ExcludeLog)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void logRequest(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        log.info(REQUEST_LOG_PATTERN,
                request.getMethod(),
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs(),
                request.getRequestURI());
    }

    @AfterReturning(pointcut = "pointCut()", returning = "response")
    public void logResponse(JoinPoint joinPoint, Object response) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        log.info(RESPONSE_LOG_PATTERN,
                request.getMethod(),
                joinPoint.getSignature().toShortString(),
                request.getRequestURI(),
                Optional.ofNullable(response).orElse(EMPTY)
        );
    }
}