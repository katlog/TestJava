package name.fw.effectivejava.examples.chapter05.item25;

import java.util.Arrays;
import java.util.List;

/**
 * @moudle: ExtractMethod
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2018年1月29日 下午8:25:13
 *
 */
public class Demo {

    public static void main(String[] args) {
        
        // Fails at runtime!
        Object[] objectArray = new Long[1];
        objectArray[0] = "I don't fit it";// Throws ArrayStoreException
        
        // Won't compile!
//        List<Long> ol = new ArrayList<Long>();//Incompatible types
//        ol.add("I don't fit it");
        
        // Why generic array creation is illegal - won.t compile!
//        List<String>[] stringLists = new List<String>[1];   //(1)
//        List<Integer> intliList = Arrays.asList(42);        //(2)
//        Object[] objects = stringLists;                     //(3)
//        objects[0] = intliList;                             //(4)
//        String s = stringLists[0].get(0);                   //(5)
    }
}
