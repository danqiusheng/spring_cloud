/*
package com.moa.multids.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * 动态创建多数据源注册到Spring中
 * Created by Administrator on 2017/10/20.
 * 方法的执行顺序是：
 * setEnvironment()-->postProcessBeanDefinitionRegistry()-->postProcessBeanFactory()
 * 多数据源注册
 *//*

public class MultipleDataSourceBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {


    protected Logger logger = LoggerFactory.getLogger(getClass());

    //作用域对象.
    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();

    //bean名称生成器.
    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    //如配置文件中未指定数据源类型，使用该默认值
    private static final Object DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";

    // 存放DataSource配置的集合;
    private Map<String, Map<String, Object>> dataSourceMap = new HashMap<String, Map<String, Object>>();


    */
/**
     * 将其注册为spring的bean对象
     *
     * @param registry 注册工厂
     * @throws BeansException
     *//*

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("MultipleDataSourceBeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry()");
        try {
            if (!dataSourceMap.isEmpty()) {
                //不为空的时候，进行注册bean.
                for (Map.Entry<String, Map<String, Object>> entry : dataSourceMap.entrySet()) {
                    Object type = entry.getValue().get("type");//获取数据源类型，没有设置为默认的数据源.
                    if (type == null) {
                        type = DATASOURCE_TYPE_DEFAULT;
                    }
                    registerBean(registry, entry.getKey(), (Class<? extends DataSource>) Class.forName(type.toString()));
                }
            }
        } catch (ClassNotFoundException e) {
            //异常捕捉.
            e.printStackTrace();
        }
    }

    */
/**
     * 注入从setEnvironment方法中读取的application.properties配置信息
     * @param beanFactory
     * @throws BeansException
     *//*

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MultipleDataSourceBeanDefinitionRegistryPostProcessor.postProcessBeanFactory()");
        //设置为主数据源;
        beanFactory.getBeanDefinition("dataSource").setPrimary(true);
        if (!dataSourceMap.isEmpty()) {
            //不为空的时候.
            BeanDefinition bd = null;
            Map<String, Object> dsMap = null;
            MutablePropertyValues mpv = null;
            for (Map.Entry<String, Map<String, Object>> entry : dataSourceMap.entrySet()) {
                bd = beanFactory.getBeanDefinition(entry.getKey());
                mpv = bd.getPropertyValues();
                dsMap = entry.getValue();
                mpv.addPropertyValue("driverClassName", dsMap.get("driverClassName"));
                mpv.addPropertyValue("url", dsMap.get("url"));
                mpv.addPropertyValue("username", dsMap.get("username"));
                mpv.addPropertyValue("password", dsMap.get("password"));
            }
        }
    }


    */
/**
     * *这个方法主要是：加载多数据源配置
     * 从application.properties文件中进行加载;
     *
     * @param environment
     *//*

    @Override
    public void setEnvironment(Environment environment) {
         */
/*
          *获取application.properties配置的多数据源配置，添加到map中，之后在postProcessBeanDefinitionRegistry进行注册。
           *//*

        //获取到前缀是"custom.datasource."的属性列表值.
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(environment, "custom.datasource.");
        //获取到所有数据源的名称.
        String dsPrefixs = propertyResolver.getProperty("names");
        String[] dsPrefixsArr = dsPrefixs.split(",");
        for (String dsPrefix : dsPrefixsArr) {
                     */
/*
                      *获取到子属性，对应一个map;
                      *也就是这个map的key就是
                      * type、driver-class-name等;
                      *//*


            Map<String, Object> dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
            //存放到一个map集合中，之后在注入进行使用.
            dataSourceMap.put(dsPrefix, dsMap);

        }
    }

    */
/**
     * 注册Bean到Spring
     *
     * @param registry
     *//*


    private void registerBean(BeanDefinitionRegistry registry, String name, Class<?> beanClass) {
        AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(beanClass);
        //单例还是原型等等...作用域对象.
        ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(abd);
        abd.setScope(scopeMetadata.getScopeName());
        // 可以自动生成name
        String beanName = (name != null ? name : this.beanNameGenerator.generateBeanName(abd, registry));
        AnnotationConfigUtils.processCommonDefinitionAnnotations(abd);
        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, beanName);
        BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);
    }

}
*/
