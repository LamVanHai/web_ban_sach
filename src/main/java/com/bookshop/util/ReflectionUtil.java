package com.bookshop.util;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {

    public static Object get(Object object, Field fieldName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String getFieldName = "get" + fieldName.getName().substring(0, 1).toUpperCase() + fieldName.getName().substring(1);
        Method method = object.getClass().getMethod(getFieldName);
        return method.invoke(object);
    }

    public static void set(Object object, Field field, Object value) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(object, value);
    }
    public static String enCryptPassword(String password){
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
    public static <T, P> void mapper(T source, P target) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = target.getClass().getDeclaredFields();
        for (int i = 0; i < sourceFields.length; i++) {
            Field sourceField = sourceFields[i];
            if (get(source, sourceField) == null) {
                continue;
            }
            for (int j = 0; j < targetFields.length; j++) {
                Field targetField = targetFields[j];
                if (sourceField.getName().equals(targetField.getName())
                        && sourceField.getType().getTypeName().equals(targetField.getType().getTypeName())) {
                    targetField.setAccessible(true);
                    targetField.set(target, get(source, sourceField));
                    continue;
                }
            }
        }
    }

}
