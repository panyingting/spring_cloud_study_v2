package com.common.study.reflect;

import com.common.study.bean.UserBean;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

public class PropertiesDescriptorDemo {


    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {

        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("age", UserBean.class);

        UserBean bean = new UserBean();


        System.out.println("propertyDescriptor2:"+propertyDescriptor.getWriteMethod().invoke(bean, false));

        Object obj = propertyDescriptor.getReadMethod().invoke(bean);

        System.out.println("read back:"+obj);
    }
}
