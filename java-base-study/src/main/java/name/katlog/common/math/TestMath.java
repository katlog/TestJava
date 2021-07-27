package name.katlog.common.math;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by fw on 2018/12/22
 */
public class TestMath {


    @Test
    public void ceil() {
        assertEquals(1.0,Math.ceil(0.3),0.000);
        assertEquals(2.0,Math.ceil(1.3),0.000);
        assertEquals(2.0,Math.ceil(1.7),0.000);
        assertEquals(0.0,Math.ceil(-0.1),0.000);
        assertEquals(-0.0,Math.ceil(-0.1),0.000);
    }

    @Test
    public void abs(){
        assertEquals(1, Math.abs(-1));

        assertEquals(2147483647, Integer.MAX_VALUE);
        assertEquals(2147483647, Math.abs(Integer.MAX_VALUE));

        // 注意，复数超范围
        assertEquals(-2147483648, Math.abs(Integer.MIN_VALUE));
        assertEquals(-2147483648, Integer.MIN_VALUE);
    }
}
