package com.liaoyb.dynamic_register_bean;

import com.liaoyb.dynamic_register_bean.bean.InterfaceBean;
import com.liaoyb.dynamic_register_bean.bean.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanDynamicRegistrationApplicationTest {
    @Autowired
    @Qualifier("testBean")
    private TestBean testBean;

    @Autowired
    @Qualifier("cglibTestBean")
    private TestBean cglibTestBean;

    @Autowired
    private InterfaceBean interfaceBean;

    @Test
    public void contextLoads() {
        testBean.hello();
        cglibTestBean.hello();

        interfaceBean.hello();
    }

}
