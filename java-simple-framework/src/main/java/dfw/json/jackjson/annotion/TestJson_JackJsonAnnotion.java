
/**  
 * @Title: TestJackJsonAnnotion.java
 * @Package: org.person.dfw.jackjson
 * @author: katlog
 * @date: 2017年4月13日 下午1:53:58
 * @version: V1.0  
 */ 
package dfw.json.jackjson.annotion;

import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * @moudle: TestJackJsonAnnotion 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年4月13日 下午1:53:58
 *
 */
public class TestJson_JackJsonAnnotion {

    @Test
    public void test() {
        fail("Not yet implemented");
    }

}

class TestPOJO{  
    TestPOJO(){}  
    TestPOJO(String name){  
        this.name = name;  
    }  
    private String name;  
    @Override  
    public String toString() {  
        return "TestPOJO{" + "name='" + name + '\'' + '}';  
    }  
}  