package com.liaoyb.dynamic_register_bean.bean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //调用逻辑
        System.out.println("jdk动态代理调用，这里是自定义逻辑");
        return null;
    }
}
