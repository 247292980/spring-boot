package com.lgp.droolsdrt.manager;

import com.lgp.droolsdrt.annotation.Fact;
import com.lgp.droolsdrt.annotation.FactProperty;
import com.lgp.droolsdrt.domain.PropertyBean;
import com.lgp.droolsdrt.domain.fact.RegisterFact;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR lgp
 * @DATE 2018/9/3 16:10
 * @DESCRIPTION 事件管理
 **/
public class FactManager {

    private static Map<String, List<PropertyBean>> propertiesMap = new HashMap<>();
    private static List<PropertyBean> commonList = new ArrayList<>();
    private static List<Class> factList = new ArrayList<>(20);
    private static Map<String, Class> eventFactMap = new HashMap<>();

    static {
        factList.add(RegisterFact.class);
        initFactDescription();
//        这个例子没有用到CustomerInfo，CustomerInfo应该在登陆后的规则使用，因为其无用故注释，但是项目逻辑有用故保留
//        commonList.addAll(buildProperty(CustomerInfo.class));
    }

    /**
     * 初始化Fact说明
     */
    private static void initFactDescription() {
        for (Class c : factList) {
            Fact fact = (Fact) c.getAnnotation(Fact.class);
            eventFactMap.put(fact.value().toString(), c);
            propertiesMap.put(fact.value().toString(), buildProperty(c));
        }
    }

    private static List<PropertyBean> buildProperty(Class c) {
        List<PropertyBean> list = new ArrayList<>();
        Fact fact = (Fact) c.getAnnotation(Fact.class);
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            PropertyBean pb = new PropertyBean();
            FactProperty propertyAnnno = f.getAnnotation(FactProperty.class);
            if (propertyAnnno == null) {
                continue;
            }
            /**
             * 如果Fact设了前缀，就在属性上增加前缀
             */
            if (fact.prefix() != null && !fact.prefix().equals("")) {
                pb.setName(fact.prefix() + "." + f.getName());
            } else {
                pb.setName(f.getName());
            }

            pb.setDesc(propertyAnnno.desc());
            pb.setFormat(propertyAnnno.format());
            String[] optionValue = propertyAnnno.optionalValue();
            if (optionValue != null && optionValue.length > 0) {
                String option = "";
                for (String str : optionValue) {
                    option += str;
                    option += ",";
                }
                pb.setOptionalValue(option.substring(0, option.length() - 1));
            }
            list.add(pb);
        }
        return list;
    }

    public static void addBean(String eventName, PropertyBean bean) {
        List<PropertyBean> beans = propertiesMap.computeIfAbsent(eventName, (key) -> new ArrayList<>());
        beans.add(bean);
    }

    public static Map<String, List<PropertyBean>> getPropertiesMap() {
        return propertiesMap;
    }

    public static List<PropertyBean> getCommonList() {
        return commonList;
    }

    public static void setCommonList(List<PropertyBean> commonList) {
        FactManager.commonList = commonList;
    }

    public static Class getFactClassByEvent(String event) {
        return eventFactMap.get(event);
    }

}
