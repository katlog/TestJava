package name.fw.effectivejava.examples.chapter10.item66;

import java.util.concurrent.atomic.AtomicLong;


/**
 * @moudle: ExtractMethod
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2018年2月11日 下午9:09:51
 *
 */
public class Demo {
    
    public static void main(String[] args) {
        
        
    }
    
    // Broken - requires synchronization
    private static volatile int nextSeiralNumber = 0;
    public static int generateSerialNumber() {
        return nextSeiralNumber++;
    }
    
    private static final AtomicLong nextSeiralNumber1 = new AtomicLong();
    public static long generateSerialNumbe1r() {
        return nextSeiralNumber1.incrementAndGet();
    }

}
