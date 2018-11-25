package com.mhc.bi.common.hadoop.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhaoshuai on 16/9/19.
 */
public class ReflectionUtil {

    /**
     * 通过结果集反射生成类对象
     * @param rs
     * @param clazz
     * @param <T>
     * @return 
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SQLException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static <T> T createObj(ResultSet rs, Class<T> clazz) throws IllegalAccessException,
            InstantiationException, SQLException, NoSuchMethodException, InvocationTargetException {
        T t = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        String fieldName, methodName;
        Method method;
        Object value;
        for (Field field : fields) {
            fieldName = field.getName();
            if ("serialVersionUID".equals(fieldName)) {
                continue;
            }
            methodName = "set".concat(fieldName.substring(0, 1).toUpperCase()).concat(fieldName.substring(1));
            method = clazz.getMethod(methodName, field.getType());
            value = rs.getObject(fieldName);
            method.invoke(t, value);
        }
        return t;
    }

}
