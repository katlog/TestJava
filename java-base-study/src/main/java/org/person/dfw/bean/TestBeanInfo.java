package org.person.dfw.bean;

import lombok.Data;
import org.junit.Test;

import java.beans.*;
import java.lang.reflect.Method;

/**
 * Created by fw on 2019/8/19
 */
public class TestBeanInfo {

    @Data
    class Person{
        private String name;
        private int age;
    }
    
    @Test
    public void Introspector() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);

        // 属性
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            System.out.println(propertyDescriptor);
            Method writeMethod = propertyDescriptor.getWriteMethod();
            System.out.println("writeMethod = " + writeMethod);
            Method readMethod = propertyDescriptor.getReadMethod();
            System.out.println("readMethod = " + readMethod);
        }

        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        for (MethodDescriptor methodDescriptor : methodDescriptors) {
            System.out.println(methodDescriptor);
        }
    }
}
