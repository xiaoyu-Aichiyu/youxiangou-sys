//package com.qqls.youxiangousys.pj.admin.common.aspect;
//
//import com.qqls.youxiangousys.pj.admin.common.annotation.RequiredLog;
//import com.qqls.youxiangousys.pj.admin.common.util.IPUtils;
//import com.qqls.youxiangousys.pj.admin.common.util.ShiroUtil;
//import com.qqls.youxiangousys.pj.admin.sys.entity.SysLog;
//import com.qqls.youxiangousys.pj.admin.sys.service.SysLogService;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.Date;
//
//@Aspect
//@Component
//@Slf4j
//public class SysLogAspect {
//    @Pointcut("bean(sysUserServiceImpl)")
//    public void logPointCut() {}
//
//    @Pointcut("@annotation(com.qqls.youxiangousys.pj.admin.common.annotation.RequiredLog)")
//    public void saveLog(){}
//
//    @Autowired
//    private SysLogService logService;
//
//    @Around("saveLog()")//经过分析保存日志要用细粒度切入点
//    //ProceedingJoinPoint：连接点，能够获取目标方法的所有信息
//    public Object saveLog(ProceedingJoinPoint jp) throws Throwable {
//        long begin = System.currentTimeMillis();
//        Object result = jp.proceed();
//        long end = System.currentTimeMillis();
//        insertLog(end - begin,jp);
//        return result;
//    }
//
//    /**
//     * 保存日志
//     * @param time 执行时长
//     * @param jp   目标方法
//     */
//    public void insertLog(long time, ProceedingJoinPoint jp) throws Exception {
//        //获取方法的签名:包含了方法的签名信息
//        //方法的签名：字节码层面的方法签名: 方法名称、参数顺序、参数类型、方法返回值、受查异常表,
//        //受查异常表指的是非运行时异常,这种异常在写代码时必须显示申明的异常。
//
//        //MethodSignature是Signature的儿子，是向下造型，需要强转
//        //JDK获取方法签名是接口的，CGLIB是获取方法签名是实现类的
//        // 原因：JDK动态代理是实现接口的，CGLIB是继承实现类的
//        MethodSignature ms = (MethodSignature) jp.getSignature();
//        //获取目标方法,这种方法JDK动态代理获取的是接口的方法，不能用
//        //Method method = ms.getMethod();//获取目标方法
//        Object obj = jp.getTarget();//获取目标对象
//        Class<?> clz = obj.getClass();//获取类对象
//        //反射获取指定方法，第一个参数为方法名，第二个参数为参数类型
//        Method method = clz.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
//        String name = method.getName();//获取方法名
//        //String type = ms.getDeclaringTypeName();//获取类型
//        String type = clz.getName();
//        String methodName = type + "." + name;
//        System.out.println(methodName);
//        Object[] args = jp.getArgs();//获取参数的方法
//        String argsStr = Arrays.toString(args);
//        String ip = IPUtils.getIpAddr();
//        System.out.println(ip);
//        //获取指定类型的注解
//        RequiredLog logStr = method.getAnnotation(RequiredLog.class);
//        String operation = logStr.value();
//        SysLog log = new SysLog();
//        log.setIp(ip);//设置ip
//        log.setCreatedTime(new Date());//设置创建日期
//        log.setMethod(methodName);//设置方法
//        log.setTime(time);//设置执行时长
//        log.setUsername(ShiroUtil.getUsername());//设置用户名
//        log.setParams(argsStr);//设置参数
//        log.setOperation(operation);//设置操作
//        logService.insertLogData(log);
//    }
//
//}
