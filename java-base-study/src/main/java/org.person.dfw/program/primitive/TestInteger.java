package org.person.dfw.program.primitive;

import org.junit.Test;

public class TestInteger {
    @Test public void test(){
        Integer i = new Integer(10);
        i = 5;
        i = Integer.valueOf(5);
        
    }
    
    @Test public void toBinaryString(){
        int i = 1111111170;
        System.out.println("Number = " + i);
       
        /* returns the string representation of the unsigned integer value 
        represented by the argument in binary (base 2) */
        System.out.println("Binary is " + Integer.toBinaryString(i));

        // returns the number of one-bits 
        System.out.println("Number of one bits = " + Integer.bitCount(i)); 
    }
}
