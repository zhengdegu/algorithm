package com.gu.algorithm.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author gu
 * @create 2020/12/30 下午4:04
 */
public class Apple {
    public String name;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("com.gu.algorithm.reflect.Apple");
        Method setPrice = aClass.getMethod("setPrice", int.class);
        Constructor<?> constructor = aClass.getConstructor();
        Object o = constructor.newInstance();
        setPrice.invoke(o, 14);
        Method getPrice = aClass.getMethod("getPrice");
        System.out.println("Apple Price:" + getPrice.invoke(o));
        //通过反射创建对象 第一种：通过 Class 对象的 newInstance() 方法。 第二种：通过 Constructor 对象的 newInstance() 方法
        Apple apple1 = (Apple) aClass.newInstance();
        Apple apple2 = (Apple) constructor.newInstance();
        Method[] methods = aClass.getMethods();
//        for (Method method : methods) {
//            System.out.println(method.getName());
//        }
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }
}
