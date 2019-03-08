package org.person.dfw.util;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void joinString() {

        String s = StringUtil.joinString(Arrays.asList(1, 3, 4, 5, 6), ",", "(", ")");
        assertEquals("(1,3,4,5,6)", s);

        s = StringUtil.joinString(Arrays.asList(1, 3, 4, 5, 6), ",");
        assertEquals("1,3,4,5,6", s);
    }

    @Test
    public void splitRemoveDuplicates(){
        String s = "123;123;4444;4545;55;55";
        assertEquals("123;4444;4545;55",StringUtil.splitRemoveDuplicates(s, ";"));
    }
}