
/**  
 * @Title: TestArray.java
 * @Package: org.person.dfw.program.primitive
 * @author: 丰伟
 * @date: 2017年5月8日 上午11:17:27
 * @version: V1.0  
 */ 
package org.person.dfw.program.primitive;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

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

    /** 有两种方式能创建成功 */
    @Test
    public void createArray_sucess(){
        ArrayList<Integer> arrayList = Lists.newArrayList(1, 2, 3, 4, 5);
        Integer[] ts1 = arrayList.toArray(new Integer[5]);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, ts1);

        Integer[] ts2 = arrayList.toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, ts2);
    }
}
