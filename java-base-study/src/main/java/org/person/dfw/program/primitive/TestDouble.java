package org.person.dfw.program.primitive;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by fw on 2019/3/7
 */
public class TestDouble {

    /** 计算精度误差 */
    @Test
    public void calculationAccuracyError(){

        assertEquals(0.8999999999999999, 2.0 - 1.1,0.00000000000000000000000);

        assertNotEquals(1/100,0.01,0.00);

        BigDecimal subtract = new BigDecimal("2.0").subtract(new BigDecimal("1.1"));
        assertEquals(subtract.doubleValue(), 0.9,0.00000000);

        BigDecimal subtract1 = new BigDecimal(2.0).subtract(new BigDecimal(1.1));
        assertNotEquals(subtract1.doubleValue(), 0.9,0.00000000);
    }

}
