package name.katlog.program.primitive;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by fw on 2020/1/13
 */
public class TestBigDecimal {

    @Test
    public void testToString(){
        BigDecimal decimal = new BigDecimal(1.1);
        // 输出：1.100000000000000088817841970012523233890533447265625
        System.out.println("decimal = " + decimal);
        double doubleValue = decimal.doubleValue();
        // 输出：1.1
        System.out.println("doubleValue = " + doubleValue);

        decimal = new BigDecimal(1.1+"");
        // 输出：1.1
        System.out.println("decimal = " + decimal);
        doubleValue = decimal.doubleValue();
        // 输出：1.1
        System.out.println("doubleValue = " + doubleValue);
    }
}
