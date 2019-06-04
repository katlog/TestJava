package org.person.dfw.common.time;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class TestLocalTime {

    @Test
    public void equals() {
        LocalDate now1 = LocalDate.now();
        LocalDate now2 = LocalDate.now();

        Assert.assertEquals(now1, now2);
    }
}
