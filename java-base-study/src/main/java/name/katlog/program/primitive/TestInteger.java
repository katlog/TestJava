package name.katlog.program.primitive;

import org.junit.Assert;
import org.junit.Test;

public class TestInteger {
    @Test public void test(){
        Integer i = new Integer(10);
        i = 5;
        i = Integer.valueOf(5);
        
    }
    
    @Test
    public void toBinaryString(){
        int i = 1111111170;
        System.out.println("Number = " + i);
       
        /* returns the string representation of the unsigned integer value 
        represented by the argument in binary (base 2) */
        System.out.println("Binary is " + Integer.toBinaryString(i));

        // returns the number of one-bits 
        System.out.println("Number of one bits = " + Integer.bitCount(i)); 
    }

    @Test
    public void Constant(){

        Assert.assertEquals(-2147483648, Integer.MIN_VALUE);
        Assert.assertEquals(2147483647, Integer.MAX_VALUE);

    }
}
