package org.person.dfw.program.primitive;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCharacter  {
    /**
     * java.lang.Character.digit(int codePoint, int radix) 在指定的基数返回指定字符（Unicode代码点）的数值。

            如果基数是不在范围内MIN_RADIX≤基数≤MAX_RADIX或如果字符是不是一个有效的数字在指定的基数-1，则返回。以下是如果至少有一个字符是一个有效的数字：
            方法isDigit(codePoint)是真正的字符的字符（或分解的单字符）的Unicode十进制数值小于指定的基数。在这种情况下的十进制数字值被返回。
            该字符是一个大写拉丁字母'A'到'Z'和它的代码是小于基数+'A' - 10。在这种情况下，codePoint描述 - 'A'+10返回。
            字符的小写拉丁字母'a'到'z'和它的代码小于radix+'A' - 10。在这种情况下，codePoint描述 - 'a'的+10返回。
            字符是一个全角大写拉丁字母A（'\ uFF21'）到Z（'\ uFF3A“）和它的代码小于radix+'\ uFF21”的 - 10。在这种情况下，codePoint描述 - '\ uFF21'+ 10返回。
            该字符是一个小写拉丁字母的全角（'\ uFF41'）到Z（'\ uFF5A“），且它的代码小于radix+'\ uFF41”的 - 10。在这种情况下，codePoint描述 - '\ uFF41'+10返回。
     */
    @Test public void digit(){
        int cp1 = 0x0034;
        int cp2 = 0x004a;
        
        int i1 = Character.digit(cp1, 8);
        int i2 = Character.digit(cp2, 8);

        // print i1, i2 values
        System.out.println( "Numeric value of cp1 in radix 8 is " + i1);
        System.out.println( "Numeric value of cp2 in radix 8 is " + i2 );
    }
    
    /**【代码点】与一个编码表中的某个字符对应的代码值。在Unicode代码点用十六进制，并加上前缀U+，
     *      例如U+0041就是字母A的代码点。Unicode的代码点可分成17个代码级别
     *            第一个代码级别称为基本的多语言级别（basicmultilingualplane），代码点从U+0m0到U+FFFF，其中包括了经典的Unicode代码
     *            其余的16个附加级别，代码点从U+10000到U+100FFFF，其中包括了一些辅助字符
     * 【代码单元】UTF16编码用不同长度的编码表示所有Unicode代码点。在基本的多语言级别中，每个字符用16位表示，通常被称为代码单元（codeunit）
     *      而辅助字符采用一对连续的代码单元进行编码。这样构成的编码值一定落人基本的多语言级别中空闲的2048字节内，通常被称为替代区域（surrogatearea)[U+D800~U+DBFF用于第一个代码单元，U+DCOO-U+DFFF用于第二个代码单元]·这样设计十分巧妙，可从中迅速地知道一个代码单元是一个字符的编码，还是一个辅助字符的第一或第二部分。倒如，对于整数集合的数学符号，它的代码点是U+1D56B,并且是用两个代码单元U+D835和U+DD6B编码的
     */
    @Test
    public void codepoint(){
    	//增补字符	𠀀
    	System.out.println("𠀀");
        //增补字符	𠀐
        System.out.println("\uD840\uDC10");
        System.out.println('\uD840'+'\uDC10');
    }

    @Test public void codePointAt(){
        char[] c = {'a', 'b', '测', '试'};

        assertEquals(97,Character.codePointAt(c, 0));
        assertEquals(98,Character.codePointAt(c, 1));
        assertEquals(27979,Character.codePointAt(c, 2));
        assertEquals(35797,Character.codePointAt(c, 3));

        assertEquals('a', 97);
        assertEquals('b', 98);
        assertEquals('测', 27979);
        assertEquals('试', 35797);
    }
    
    /**判断字符范围*/
    @Test public void isJavaIdentifierPart(){
    	
    	//isJavaIdentifierPart检查变量名字母是否可用
    	
    	//字母包括，'A'~'Z'、'a'~'z'、'_'或在某种语言中代表字母的任何Unicode字符
        assertTrue(Character.isJavaIdentifierPart('a'));

    	//'+'和'©'这样的符号不能出现在变量名中，空格也不行。变量名中所有的字符都是有意义的
        assertFalse(Character.isJavaIdentifierPart('©'));

    }
}
