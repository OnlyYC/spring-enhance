package com.liaoyb.dynamic_register_bean.bean;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy  implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] arg2,
                            MethodProxy proxy) throws Throwable {
        System.out.println("前置代理");
        //对接入点的放行(Spring aop中接入点)也即实现类方法，要是不掉用此方法，则代理类中的say()方法将不会被执行
        Object result=proxy.invokeSuper(obj, arg2);
        System.out.println("后置代理");
        return null;
    }
}
