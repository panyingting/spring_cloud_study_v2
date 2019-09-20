package com.common.study.reflect;

import com.common.study.bean.UserBean;

import java.beans.*;
import java.util.Arrays;

public class IntrospectorDemo {


    public static void main(String[] args) throws IntrospectionException {

        UserBean userBean = new UserBean();


        BeanInfo beanInfo = Introspector.getBeanInfo(UserBean.class);

        int index = beanInfo.getDefaultPropertyIndex();

        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();

        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();

        Arrays.stream(descriptors).forEach( e->{
            System.out.println(e.getName() + " :" + e.getWriteMethod() + " :" + e.getReadMethod());
        });

        System.out.println(methodDescriptors);
    }
}
