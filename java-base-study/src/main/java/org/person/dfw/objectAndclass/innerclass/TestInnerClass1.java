
/**  
 * @Title: TestInnerClass1.java
 * @Package: org.person.dfw.objectAndclass.innerclass
 * @author: katlog
 * @date: 2017年5月9日 上午9:48:41
 * @version: V1.0  
 */ 
package org.person.dfw.objectAndclass.innerclass;

import org.junit.Test;

/**
 * @moudle: TestInnerClass1 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年5月9日 上午9:48:41
 *
 */
public class TestInnerClass1 {
    @Test public void innerClass(){
        
    }
}


class Circle {
    private double radius = 0;
 
    public Circle(double radius) {
        this.radius = radius;
        getDrawInstance().drawSahpe();   //必须先创建成员内部类的对象，再进行访问
    }
     
    private Draw getDrawInstance() {
        return new Draw();
    }
     
    class Draw {     //内部类
        public void drawSahpe() {
            System.out.println(radius);  //外部类的private成员
        }
    }
}