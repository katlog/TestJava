
/**  
 * @Title: TestArray.java
 * @Package: org.person.dfw.program.primitive
 * @author: 丰伟
 * @date: 2017年5月8日 上午11:17:27
 * @version: V1.0  
 */ 
package org.person.dfw.program.primitive;

import org.junit.Test;

public class TestArray {
    final String intArrStr = "1,2,3,4,5";
    
    
    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void arrayOut(){
        String[] ints = intArrStr.split(",");
        String last = ints[5];
    }
}
