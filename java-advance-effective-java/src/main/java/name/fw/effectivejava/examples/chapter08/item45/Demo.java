package name.fw.effectivejava.examples.chapter08.item45;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @moudle: ExtractMethod
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2018年1月19日 下午7:55:09
 *
 */
public class Demo {
    
    public static void main(String[] args) {
        
        List<Element> c = new ArrayList<Element>();
        
        // Preferred idiom for iterating over a collection
        for (Element e : c) {
            
        }
        
        List c1 = new ArrayList();
        // No for-each loop or generics before release 1.5
        for (Iterator iterator = c1.iterator(); iterator.hasNext();) {
            ((Element)iterator.next()).doSomething();
        }
        
        
        Iterator<Element> i = c.iterator();
        while (i.hasNext()) {
            ((Element)i.next()).doSomething();
        }
        
        Iterator<Element> i2 = c1.iterator();
        while (i.hasNext()) {   // bug!
            ((Element)i2.next()).doSomething();
        }
        
        for (int j = 0, n = expensiveComputation(); j < n ; j++) {
            doSomething();
        }
    }
    
    static void doSomething(){};
    static int expensiveComputation(){ return 1 ;};
    
}
interface Element{ void doSomething();}

