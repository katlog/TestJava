package org.person.dfw.collection;

import org.junit.Test;
import org.person.dfw.Person;

import java.util.WeakHashMap;

/**
 * Created by fw on 2020/7/6
 */
public class TestWeakHashMap {

    @Test
    public void test(){
        WeakHashMap<Person,Person> weakHashMap=new WeakHashMap<>();
        Person di = new Person("di", 18);
        Person li = new Person("li", 20);
        weakHashMap.put(di, li);
        System.out.println(weakHashMap);

        System.gc();
        System.out.println(weakHashMap);

        // key 没有强引用时会全部回收
        di = null;
        System.gc();
        System.out.println(weakHashMap);
    }
}
