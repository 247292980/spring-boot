package com.lgp.droolsdrt.util;


import org.springframework.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

/**
 * copy工具类
 */
public class CopyUtil {

    private static final Map<String, BeanCopier> beanCopierMap = new HashMap<String, BeanCopier>();

    /**
     * 使用Cglib的BeanCopier实现Bean的拷贝
     *
     * @param source
     * @param target
     */
    public static void copyPropertiesCglib(Object source, Object target) {
        String beanKey = generateBeanKey(source.getClass(), target.getClass());
        BeanCopier copier = null;
        if (!beanCopierMap.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopierMap.put(beanKey, copier);
        } else {
            copier = beanCopierMap.get(beanKey);
        }
        copier.copy(source, target, null);
    }

    public static String generateBeanKey(Class<?> source, Class<?> target) {
        return source.getName() + "@" + target.getName();
    }

}
