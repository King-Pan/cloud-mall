package cn.druglots.mall.core.aspect;

import cn.druglots.mall.common.utils.FastJsonUtils;
import cn.druglots.mall.common.utils.IpUtils;
import cn.druglots.mall.sys.entity.SysLog;
import cn.druglots.mall.sys.service.ISysLogService;
import cn.druglots.mall.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.common.aspect
 * @Author: king-pan
 * @CreateTime: 2019-09-09 11:11
 * @Description: 自定义日志记录器-切面
 */
@Slf4j
@Component
public class LogAspect {

    @Autowired
    private ISysLogService logService;

    /**
     * 定义切面
     */
    @Pointcut("@annotation(cn.druglots.mall.core.aspect.Log)")
    public void serviceAspect() {
    }

    @Before("serviceAspect()")
    public void doBefore(JoinPoint joinPoint) {
        //前置通知
        if (log.isDebugEnabled()) {
            log.debug("-->: 前置通知");
        }
    }


    @AfterReturning(pointcut = "serviceAspect()", returning = "rvt")
    public Object doAfterReturning(JoinPoint joinPoint, Object rvt) {
        if (log.isDebugEnabled()) {
            log.debug("-->: AfterReturning");
        }
        try {
            //记录日志
        } catch (Exception e) {
            log.error("记录日志出错", e);
        }
        return rvt;
    }

    /**
     * 环绕通知
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "serviceAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object rvt;
        SysLog sysLog = new SysLog();
        long beginTime = 0L;
        if (log.isDebugEnabled()) {
            log.debug("-->: 开始记录日志");
        }
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            // log注解
            Log logAn = method.getAnnotation(Log.class);
            if (logAn != null) {
                //注解上的描述
                sysLog.setModuleName(logAn.module());
                sysLog.setOperation(logAn.fp());
                sysLog.setDescription(logAn.description());
            }

            User user = (User) SecurityUtils.getSubject().getPrincipal();

            if (user != null) {
                //记录用户名
                sysLog.setUserName(user.getUserName());
            }
            sysLog.setAccessTime(new Date());

            //请求的参数
            Object[] args = joinPoint.getArgs();
            try {
                sysLog.setParams(FastJsonUtils.getBeanToJson(args));
            } catch (Exception e) {
                log.error("记录日志解析参数失败 : " + e.getMessage());
            }

            //获取请求方法
            sysLog.setMethodName(method.getName());
            //获取request
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
                //获取用户请求地址
                sysLog.setUrl(request.getRequestURI());
                //设置IP地址
                sysLog.setIp(IpUtils.getIpAddr(request));
            }


            beginTime = System.currentTimeMillis();
            rvt = joinPoint.proceed();
            //执行时长(毫秒)
            sysLog.setTime(System.currentTimeMillis() - beginTime);
            sysLog.setResult(FastJsonUtils.getBeanToJson(rvt));
            sysLog.setStatus("1");
            //记录成功
        } catch (Throwable throwable) {
            //记录失败
            sysLog.setResult("访问失败");
            sysLog.setErrorMsg(throwable.getMessage());
            sysLog.setStatus("0");
            sysLog.setTime(System.currentTimeMillis() - beginTime);
            throw throwable;
        }
        if (log.isDebugEnabled()) {
            log.debug("-->: 结束记录日志");
        }
        logService.save(sysLog);
        return rvt;
    }

    @After("serviceAspect()")
    public void doAfter(JoinPoint joinPoint) {
        if (log.isDebugEnabled()) {
            log.debug("After...");
        }
    }
}
