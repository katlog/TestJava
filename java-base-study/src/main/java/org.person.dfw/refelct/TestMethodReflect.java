package org.person.dfw.refelct;

import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Convert;
import java.lang.reflect.Method;

import static org.junit.Assert.assertNotNull;

/**
 * Created by dell on 2018/4/13
 */
public class TestMethodReflect {


    public void method1(){

    }

    @Convert
    public String method2( String[] strings){
        return strings[0];
    }

    @Test
    public void test() throws NoSuchMethodException {

        Method test = TestMethodReflect.class.getMethod("test", null);
        assertNotNull(test);

        Method method2 = TestMethodReflect.class.getMethod("method2", String[].class);
        method2.isVarArgs();

    }
}
