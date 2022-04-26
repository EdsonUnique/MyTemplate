package edson.MyTemplate.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @Author: yangxi
 * @Date: 2021/7/1 17:25
 */
@Aspect
@Component
public class AopHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(edson.MyTemplate.controller..*)")
    void daoAspect(){
    }

    @Around("daoAspect()")
    public Object handleDaoAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("--------------------- ajajajajajajajaja----------");
        logger.info("controller方法：{},时间为：{} come in aspect",joinPoint.getSignature(),new Date());
        return joinPoint.proceed();//需要返回  否则无法将json结果返回给前端页面
    }


}
