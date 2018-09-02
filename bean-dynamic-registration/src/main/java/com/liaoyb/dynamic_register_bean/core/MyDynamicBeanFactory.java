package com.liaoyb.dynamic_register_bean.core;

import com.liaoyb.dynamic_register_bean.bean.CglibInvocationHandler;
import com.liaoyb.dynamic_register_bean.bean.CglibProxy;
import com.liaoyb.dynamic_register_bean.bean.InterfaceBean;
import com.liaoyb.dynamic_register_bean.bean.JdkInvocationHandler;
import com.liaoyb.dynamic_register_bean.bean.TestBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cglib.proxy.CallbackHelper;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class MyDynamicBeanFactory implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableListableBeanFactory;

        //类定义BeanDefinition(必须是能够实例化的)
        defaultListableBeanFactory.registerBeanDefinition("testBean", classBeanDefinition());


        defaultListableBeanFactory.registerBeanDefinition("jdkInterfaceBean", jDKDynamicProxyDefinition());

        defaultListableBeanFactory.registerBeanDefinition("cglibTestBean", cglibProxyDefinition());
    }

    /**
     * 直接通过class定义BeanDefinition
     *
     * @return
     */
    public BeanDefinition classBeanDefinition() {
        //这里class不能是接口、抽象类，必须是能够实例化的
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(TestBean.class).getRawBeanDefinition();
        beanDefinition.setAutowireCandidate(true);
        return beanDefinition;
    }

    /**
     * jdk动态代理方式（jdk动态代理会生成一个原接口对象的实现类，并且是继承于Proxy的）
     *
     * @return
     */
    public BeanDefinition jDKDynamicProxyDefinition() {
        Class proxyClass = Proxy.getProxyClass(this.getClass().getClassLoader(), new Class<?>[]{InterfaceBean.class});

        JdkInvocationHandler jdkInvocationHandler = new JdkInvocationHandler();
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(proxyClass)
                .addConstructorArgValue(jdkInvocationHandler).getRawBeanDefinition();
        beanDefinition.setAutowireCandidate(true);
        return beanDefinition;
    }

    /**
     * cglib方式动态代理方式（jdk动态代理会生成一个原接口对象的实现类，并且是继承于Proxy的）
     *
     * @return
     */
    public BeanDefinition cglibProxyDefinition() {
        CglibInvocationHandler cglibInvocationHandler = new CglibInvocationHandler();
        CglibProxy cglibProxy = new CglibProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestBean.class);

        CallbackHelper callbackHelper = new CallbackHelper(TestBean.class, new Class[]{}) {
            @Override
            protected Object getCallback(Method method) {
                System.out.println("[cglib]CallbackHelper.getCallBack 代理该方法：" + method);
                return cglibInvocationHandler;
            }
        };

        //Enhancer必须设置Callbacks
//        enhancer.setCallbackFilter(callbackHelper);
//        enhancer.setCallbacks(callbackHelper.getCallbacks());
//
        enhancer.setCallback(new CglibProxy());
        Class proxyClass = enhancer.create().getClass();


        //通过class定义BeanDefinition
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(proxyClass).getRawBeanDefinition();
        beanDefinition.setAutowireCandidate(true);
        return beanDefinition;
    }


}
