
/**  
 * @Title: HeapOOM.java
 * @Package: org.dfw.jvm.overflow
 * @author: 丰伟
 * @date: 2017年4月28日 上午9:42:06
 * @version: V1.0  
 */ 
package name.dfw.jvm.item02;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author zzm
 */
public class HeapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}