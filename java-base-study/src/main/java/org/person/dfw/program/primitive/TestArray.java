
/**  
 * @Title: TestArray.java
 * @Package: org.person.dfw.program.primitive
 * @author: katlog
 * @date: 2017年5月8日 上午11:17:27
 * @version: V1.0  
 */ 
package org.person.dfw.program.primitive;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestArray {
    final String intArrStr = "1,2,3,4,5";
    
    
    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void arrayOut(){
        String[] ints = intArrStr.split(",");
        String last = ints[5];
    }

    /** 数组不能进行类型转换 */
    @Test(expected = ClassCastException.class)
    public void createArray_castException(){
        ArrayList<Integer> arrayList = Lists.newArrayList(1, 2, 3, 4, 5);
        Integer[] objects = (Integer[]) arrayList.toArray();
    }

    /** 都能创建成功（注意toArray中数组长度的影响） */
    @Test
    public void createArray_success(){
        ArrayList<Integer> arrayList = Lists.newArrayList(1, 2, 3, 4, 5);

        Integer[] ts1 = arrayList.toArray(new Integer[5]);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, ts1);

        Integer[] ts2 = arrayList.toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, ts2);

        Integer[] ts3 = arrayList.toArray(new Integer[3]);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, ts3);

        Integer[] ts4 = arrayList.toArray(new Integer[6]);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, null}, ts4);
    }


    /** 数组的协变: */
    @Test(expected = ArrayStoreException.class)
    public void arrayCovariance(){
        List<String>[] ls;
        ls = new List[]{Lists.newArrayList(1)};

        Object[] obs = ls;
        obs[0] = new Integer(1);
    }

    /** thinking in java */
    @Test
    public void arrayCovariance1(){
        List<String>[] ls;
        List[] la = new List[10];
        // "Unchecked" warning
        ls = (List<String>[]) la;
        ls[0] = new ArrayList<String>();
        // Compile-time checking produces an error:
        //! ls[1] = new ArrayList<Integer>();

        // The problem: List<String> is a subtype of Object
        // So assignment is OK
        Object[] objects = ls;
        // Compiles and runs without complaint:
        objects[1] = new ArrayList<Integer>();
    }


    /** 生成 泛型数组 */
    class Peel<T>{}
    class Banana{}
    @Test
    public void genericArray(){
        // Peel<Banana>[] peels = new Peel<Banana>(10); //Illegal
        Peel<Banana>[] peels =new Peel[]{new Peel(),new Peel()};
        peels[1] = new Peel<>();
        peels[1] = new Peel<Banana>();
    }
}
