## 基于BeanFactoryPostProcessor动态注册bean

实现BeanFactoryPostProcessor接口，在容器初始化时，通过postProcessBeanFactory方法中beanFactory注册BeanDefinition