package org.person.dfw.common.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fw on 2018/12/22
 */
public class TestMath {
    @Test
    public void ceil() {
        Assert.assertEquals(1.0,Math.ceil(0.3),0.000);
        Assert.assertEquals(2.0,Math.ceil(1.3),0.000);
        Assert.assertEquals(2.0,Math.ceil(1.7),0.000);
        Assert.assertEquals(0.0,Math.ceil(-0.1),0.000);
        Assert.assertEquals(-0.0,Math.ceil(-0.1),0.000);
    }
}
