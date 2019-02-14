package org.person.dfw.util;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void joinString() {

        String s = StringUtil.joinString(Arrays.asList(1, 3, 4, 5, 6), ",", "(", ")");
        System.out.println("s = " + s);

        s = StringUtil.joinString(Arrays.asList(1, 3, 4, 5, 6), ",");
        System.out.println("s = " + s);


    }
}