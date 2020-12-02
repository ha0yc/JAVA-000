package org.haoyc.assignment.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.haoyc.assignment.annotation.ReadOnly;
import org.haoyc.assignment.config.DatasourceConfig;
import org.haoyc.assignment.holder.DynamicDataSourceContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;

@Aspect
@Component
public class ShardingAspect {
    @Pointcut("execution(public * org.haoyc.assignment.handler.AbstractShardingHandler + .*(*))")
    public void dataSourcePointCut(){

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        if (getReadOnlyAnnotation(joinPoint) != null) {
            Random random = new Random(3);
            DynamicDataSourceContextHolder.setContextKey("SLAVE" + random.nextInt());
        } else {
            DynamicDataSourceContextHolder.setContextKey(DatasourceConfig.MASTER);
        }

        try{
            return joinPoint.proceed();
        }finally {
            DynamicDataSourceContextHolder.removeContextKey();
        }
    }

    /**
     * 根据类或方法获取数据源注解
     */
    private ReadOnly getReadOnlyAnnotation(ProceedingJoinPoint joinPoint){
        Class<?> targetClass = joinPoint.getTarget().getClass();
        ReadOnly readOnlyAnno = targetClass.getAnnotation(ReadOnly.class);
        // 先判断类的注解，再判断方法注解
        if(Objects.nonNull(readOnlyAnno)){
            return readOnlyAnno;
        }else{
            MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
            return methodSignature.getMethod().getAnnotation(ReadOnly.class);
        }
    }
}
