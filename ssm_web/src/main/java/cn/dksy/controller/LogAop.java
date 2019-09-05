package cn.dksy.controller;

import cn.dksy.entity.SysLog;
import cn.dksy.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author JAVASM
 * @title: LogAop
 * @projectName SSM_itcast企业权限管理系统项目实战
 * @description: TODO
 * @date 2019/9/1 12:38
 */
@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    //开始时间
    private Date visitTime;
    //访问的类
    private Class clazz;
    //访问方法
    private Method method;;
//    @Pointcut("execution(* cn.dksy.controller.*.*(..)))")
//    public void pointCut(){};
    /**
     * 前置通知
     * 获取开始时间
     * 访问的类、方法
     * @param jp
     */
    @Before("execution(* cn.dksy.controller.*.*(..)))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //获取访问时间
        visitTime = new Date();
        //获取具体访问的类对象
        clazz = jp.getTarget().getClass();
        //获取访问方法的名称
        String methodName = jp.getSignature().getName();
        //获取方法的参数
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0){
            // 只能获取无参数方法
            method = clazz.getMethod(methodName);
        }else{
            // 有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            //获取方法名
            method = clazz.getMethod(methodName, classArgs);
        }
    }

    /**
     * 后通知
     *主要获取日志中其它信息，时长、ip、url...
     * @param jp
     */
    @After("execution(* cn.dksy.controller.*.*(..)))")
    public void doAfter(JoinPoint jp) throws Exception {
        //获取访问时长
        long time  = System.currentTimeMillis()- visitTime.getTime();

        //获取url
        String url = "";
        if (clazz != null && method != null && clazz != LogAop.class){
            //1、获取类上的注解的value值 @RequestMapping对象
           RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
           if (clazzAnnotation != null){
               String[] classValue = clazzAnnotation.value();
               //获取方法上的@RequestMapping对象

               RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
               if (methodAnnotation != null){
                   String[] methodValue = methodAnnotation.value();
                   url = classValue[0]+methodValue[0];
               }
           }
        }

        //获取访问的ip  通过request对象很容易获取  web.xml配置监听器  本类直接注入
        String ip = request.getRemoteAddr();


        //获取当前操作的对象的用户名 两种方式： 1、借助security  2：借助request
        //从上文中获取当前登录的用户
        SecurityContext securityContext = SecurityContextHolder.getContext();
        //Security中的user
        User user = (User) securityContext.getAuthentication().getPrincipal();
        String username = user.getUsername();

       //request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");  获取到的是User



        //将日志信息封装到实体类
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);
        sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]" + method.getName());
        if (! "cn.dksy.controller.SysLogController".equals(clazz.getName())){
            sysLogService.save(sysLog);
        }



        //调用service完成数据库存储操作
    }

}