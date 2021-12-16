package edson.MyTemplate.multiDataSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description: 通过拦截 @DS 注解，在其执行之前处理设置当前执行SQL的数据源的信息，
 * CONTEXT_HOLDER.set(dataSourceType)这里的数据源信息从我们设置的注解上面获取信息，
 * 如果没有设置就是用默认的数据源的信息。
 */
@Aspect
@Order(-1)
@Component
@EnableAspectJAutoProxy(exposeProxy = true,proxyTargetClass = true)
public class DataSourceAspect {

    //定义切点
    @Pointcut("@annotation(DS)")
    public void dsPointCut() {

    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DS dataSource = method.getAnnotation(DS.class);
        if (dataSource != null) {
            //通过注解值设置数据源
            DynamicDataSourceContextHolder.setDataSource(dataSource.value());
        }
        try {
            return point.proceed();
        } finally {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDataSource();
        }
    }
}
