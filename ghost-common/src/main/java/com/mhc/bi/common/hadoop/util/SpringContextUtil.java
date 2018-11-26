package com.mhc.bi.common.hadoop.util;

import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author 宫本
 * @date 2018/11/26
 * @description 获取上下文
 */
public class SpringContextUtil implements ApplicationContextAware {
    private SpringContextUtil(){}
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        setApplicationContextutil(appContext);
    }

    public static void setApplicationContextutil(ApplicationContext appContext){
        applicationContext = appContext;
    }

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public static <T> Map<String, T> getBeanOfType(Class<T> clazz){
        return applicationContext.getBeansOfType(clazz);
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
