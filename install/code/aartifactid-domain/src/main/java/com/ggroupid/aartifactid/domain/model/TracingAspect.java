package com.ggroupid.aartifactid.domain.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Slf4j
@Component
public class TracingAspect {

    @Pointcut("execution(* com.ggroupid.aartifactid..*Controller*.*(..))")
    private void controller() {
    }

    @Pointcut("execution(* com.ggroupid.aartifactid..*UseCase*.*(..))")
    private void useCase() {
    }

    @Pointcut("execution(public !void org.springframework.data.repository.Repository+.*(..))")
    private void repository() {
    }

    @Pointcut("within(@com.ggroupid.aartifactid.domain.model.Traceable *) || @annotation(com.ggroupid.aartifactid.domain.model.Traceable)")
    private void traceable() {
    }

    @Pointcut("execution(* com.ggroupid.aartifactid..*Mapper*.*(..))")
    private void mapper() {
    }

    @Before("repository() || useCase() || traceable() || controller() ")
    private void logMethodInvocation(JoinPoint joinPoint) {
        log("INVOCATION {}", getMethodInvocationInfo(joinPoint));
    }

    @Around("repository() || useCase() || traceable() || controller() ")
    private Object logTimeElapsedInMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long timeElapsed = System.currentTimeMillis() - startTime;

        String returnType = joinPoint.getSignature() instanceof MethodSignature
                ? ((MethodSignature) joinPoint.getSignature()).getReturnType().getName()
                : "Unknown";

        String resultLog = "void".equals(returnType) ? "void" : returnValue.toString();

        log("COMPLETED ({} ms) {}: {}", timeElapsed, getMethodInvocationInfo(joinPoint), resultLog);

        return returnValue;
    }

    @AfterReturning(value = "mapper()", returning = "returnValue")
    private void logMapping(JoinPoint joinPoint, Object returnValue) {
        log("MAPPING {}: {} => {}", getMethodInvocationInfo(joinPoint), joinPoint.getArgs()[0], returnValue);
    }

    private static void log(String message, Object... args) {
        log.trace(message, args);
    }

    private static String getMethodInvocationInfo(JoinPoint joinPoint) {
        return String.format("%s.%s(%s)",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Optional.ofNullable(joinPoint.getArgs()).map(args -> Stream.of(args)
                        .map(Objects::toString)
                        .collect(Collectors.joining(", "))).orElse(Strings.EMPTY));
    }

}