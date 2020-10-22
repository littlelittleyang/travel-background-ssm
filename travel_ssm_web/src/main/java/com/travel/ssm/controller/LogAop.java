package com.travel.ssm.controller;

import com.travel.ssm.domain.SysLog;
import com.travel.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;
    private Class clazz;
    private Method method;

    @Before("execution(* com.travel.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {

        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();

        Object[] args = jp.getArgs();
        if (args == null || args.length == 0) {

            method = clazz.getMethod(methodName);
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }
    }

    @After("execution(* com.travel.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) {
        long time = System.currentTimeMillis() - visitTime.getTime();

        String url = "";

        if (clazz != null && method != null && clazz != LogAop.class) {
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();

                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();

                    url = classValue[0] + methodValue[0];
                }
            }
        }
        //获取访问的ip地址
        String ip = request.getRemoteAddr();

        //获取当前操作用户
        // 可以通过securityContext获取
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //将日志封装到SysLog对象
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]" + clazz.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);

        sysLogService.save(sysLog);

    }
}
