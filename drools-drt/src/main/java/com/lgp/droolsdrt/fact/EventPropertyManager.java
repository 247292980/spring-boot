package com.lgp.droolsdrt.fact;

import com.lgp.droolsdrt.annotation.Fact;
import com.lgp.droolsdrt.annotation.FactProperty;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR lgp
 * @DATE 2018/9/3 16:10
 * @DESCRIPTION
 **/
public class EventPropertyManager {

    private static Map<String, List<PropertyViewBean>> propertiesMap = new HashMap<>();
    private static List<PropertyViewBean> commonList = new ArrayList<>();
    private static List<Class> factList = new ArrayList<>(20);
    private static Map<String, Class> eventFactMap = new HashMap<>();

    static {
        factList.add(RegisterFact.class);
        initFactDescription();
        commonList.addAll(buildPropertyView(CustomerInfo.class));
    }

    private EventPropertyManager() {
    }

    /**
     * 初始化Fact说明
     */
    private static void initFactDescription() {
        for (Class c : factList) {
            Fact fact = (Fact) c.getAnnotation(Fact.class);
            eventFactMap.put(fact.value().toString(), c);
            propertiesMap.put(fact.value().toString(), buildPropertyView(c));
        }
    }

    private static List<PropertyViewBean> buildPropertyView(Class c) {

        List<PropertyViewBean> list = new ArrayList<>();
        Fact fact = (Fact) c.getAnnotation(Fact.class);
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            PropertyViewBean pb = new PropertyViewBean();
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

    public static void addBean(String eventName, PropertyViewBean bean) {
        List<PropertyViewBean> beans = propertiesMap.computeIfAbsent(eventName, (key) -> new ArrayList<>());
        beans.add(bean);
    }

    public static Map<String, List<PropertyViewBean>> getPropertiesMap() {
        return propertiesMap;
    }

    public static List<PropertyViewBean> getCommonList() {
        return commonList;
    }

    public static void setCommonList(List<PropertyViewBean> commonList) {
        EventPropertyManager.commonList = commonList;
    }

    public static Class getFactClassByEvent(String event) {
        return eventFactMap.get(event);
    }

}
