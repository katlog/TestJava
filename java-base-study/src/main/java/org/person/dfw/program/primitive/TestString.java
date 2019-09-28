
/**  
 * @Title: TestString.java
 * @Package: org.person.dfw.lang
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月25日 下午3:30:26
 * @version: V1.0  
 */ 
package org.person.dfw.program.primitive;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static org.junit.Assert.*;
/**
 * @moudle: TestString 
 * @version:v1.0
 *
 */
public class TestString {
    private static final String UTF_8 = "utf-8";
    private static final String UTF_16 = "utf-16";
    private static final String ISO_8859_1 = "iso-8859-1";

    @Test
    public void _constructor() {
        //返回一个特定的子数组的char数组参数的字符串表示形式(offset,offset+length]。2是起始位置，3是长度
        assertEquals("cde", new String(new char[]{'a', 'b', 'c', 'd', 'e', 'f'}, 2, 3));
    }
    
    /**
     * 测试字符串长度，和编码格式相关的部分
     * 【代码点】：指编码字符集中，字符所对应的数字。有效范围从U+0000到U+10FFFF。
     *      基本字符：U+0000到U+FFFF
     *      增补字符：U+10000到U+10FFFF
     * 【代码单元】：对代码点进行编码得到的1或2个16位序列（UTF-16）。
     *      其中基本字符的代码点直接用一个相同值的代码单元表示，
     *      增补字符的代码点用两个代码单元进行编码，编码值来自U+D800到U+DFFF，这个范围内没有数字用于表示字符，因此程序可以识别出当前字符是单单元的基本字符，还是双单元的增补字符
     */
    @Test public void _code() {
    		
        String sentence = "abc中文𠀀𠀐";    								//增补字符：\uD840\uDC10 𠀀：𠀐:\uD840\uDC10
        System.out.println(sentence.length());								// 9个单元code units
        System.out.println(sentence.codePointCount(0, 9));					// 7个代码点code points
        
        for (int i = 0; i < 9; i++) {
			System.out.print(sentence.charAt(i)+"\t");						//charAt查看代码单元
		}
        System.out.println();
        
        for (int i = 0; i < 9; i++) {
        	if (Character.isSupplementaryCodePoint(sentence.codePointAt(i))) 
				i++;
        	System.out.print((char)sentence.codePointAt(i)+"\t");			//charAt查看代码单元
		}
        System.out.println();
        
        //isSupplementaryCodePoint确定指定的字符（Unicode代码点）是否是在增补字符范围内
        for (int j = 0; j < 7; j++) {										// i=5,6 true  i=0,1,3,4 false
        	System.out.println(Character.isSupplementaryCodePoint(sentence.charAt(j)));
		}
        
    }
    @Test
    public void testStringLength(){
        /**
         * 字符串由char序列构成，字符数据类型采用UTF-16编码表示Unicode代码点的代码单元
         *      多数常用Unicode字符用一个代码单元表示，而辅助字符需要用一对代码单元表示
         */
        String strEn = "AHello!";
        String strCn = "㍩嬛※௹虓㍿曉☬嚻⺷⺗♂ゅ♝〠♯♮₪큐۩ﺴ ﺴ۩〶𠀀";
        
        System.out.println("strEn length:"+strEn.length());
        System.out.println("strCn length:"+strCn.length());
        
        
        int strEnl = strEn.codePointCount(0, strEn.length());
        int strCnl = strCn.codePointCount(0, strCn.length());
        System.out.println("strEn code length:"+strEnl);
        System.out.println("strEn code length:"+strCnl);
        
        for (int i = 0; i < strEn.length(); i++) {
            //charAt(int index)返回给定位置的代码单元
            //codePointAt(int index)返回从给定位置开始或结束的代码点
            //offsetByCodePoints(int startIndex,int cpCount)返回从startIndex开始，位移cpCount后的代码点索引
            System.out.println(strEn.charAt(i)+":"+strEn.codePointAt(i));
        }
        
        for (int i = 0; i < strCn.length(); i++) {
            System.out.println(strCn.charAt(i)+":"+strCn.codePointAt(i));
        }
    }

    
    @Test public void test2() {
        char[] c = Character.toChars(Integer.parseInt("1D306", 16));//1D306是一个辅助平面字符  转成10进制为119558
        System.out.println(Character.codePointAt(c, 0));//输出119558，这个是1D306对应的10进制值  
        System.out.println(Character.codePointAt(c, 1));//输出57094，这个是c[1]对应字符的10进制值  
        System.out.println(new String(c).codePointAt(0));//输出119558，这个是1D306对应的10进制值  
        System.out.println(new String(c).codePointAt(1));//输出57094，这个是c[1]对应字符的10进制值  
        String str = "abcdefg" + new String(c);  
        System.out.println(str.length());//9  
        System.out.println(str.codePointCount(0, str.length()));//8  
    }
    
    @Test
    public void getBytes() throws UnsupportedEncodingException{
       String str = "中国ABC";
       System.out.println(str+"-默认编码:"+Arrays.toString(str.getBytes())+"---默认解码："+new String(str.getBytes()));
       System.out.println(str+"-utf-8编码:"+Arrays.toString(str.getBytes(UTF_8))+"---对应解码："+new String(str.getBytes(UTF_8), UTF_8));
       System.out.println(str+"-utf-16编码:"+Arrays.toString(str.getBytes(UTF_16))+"---对应解码："+new String(str.getBytes(UTF_16), UTF_16));
       System.out.println(str+"-iso-8859-1编码:"+Arrays.toString(str.getBytes(ISO_8859_1))+"---对应解码："+new String(str.getBytes(ISO_8859_1), ISO_8859_1));
    }
    
    @Test
    public void split(){

        assertArrayEquals(new String[]{"123", "3", "33"}, "123,3,33".split(","));

        assertArrayEquals(new String[]{"a", "a", "a","|","b", "b", "b","|","c", "c", "c"}, "aaa|bbb|ccc".split("|"));

    	//【注意】转义字符：字符"|","*","+","$"都得加上转义字符，前面加上"\\"
        assertArrayEquals(new String[]{"aaa", "bbb", "ccc"}, "aaa|bbb|ccc".split("\\|"));
        assertEquals("aaa", "aaa".split("\\|")[0]);

    	//而如果是"\"，那么就得写成"\\\\"。
        assertArrayEquals(new String[]{"aaa", "bbb", "ccc"}, "aaa\\bbb\\ccc".split("\\\\"));

    	//有多个分割符时,可以用"|"作为连字符。
        assertArrayEquals(new String[]{"aaa", "bbb", "c","cc"}, "aaa|bbb|c$cc".split("\\||\\$"));

    }
    
    @Test
    public void indexOf(){
    	//1、int indexOf(String str) ：返回第一次出现的指定子字符串在此字符串中的索引。 
    	//2、int indexOf(String str, int startIndex)：从指定的索引处开始，返回第一次出现的指定子字符串在此字符串中的索引。 
    	//3、int lastIndexOf(String str) ：返回在此字符串中最右边出现的指定子字符串的索引。 
    	//4、int lastIndexOf(String str, int startIndex) ：从指定的索引处开始向后搜索，返回在此字符串中最后一次出现的指定子字符串的索引。

        assertEquals(0, "123456789".indexOf("1"));
        assertEquals(-1, "123456789".indexOf("a"));
        assertEquals(1, "123456789".indexOf("23"));

        assertEquals(4, "123456789".indexOf("56"));
        assertEquals(4, "123456789".indexOf("56", 4));
        assertEquals(-1, "123456789".indexOf("56", 5));
    }
    
    @Test
    public void substring(){
    	//substring 截取 [begin,end)
    	assertEquals("3456789","123456789".substring(2));
        assertEquals("123","123456789".substring(0, 3));

    	//常用按照分割符号来获取字符串   常用方式
        assertEquals("123456", ("123456:789".substring(0, "123456:789".indexOf(":"))));
        assertEquals("89", ("123456:7:89".substring("123456:7:89".lastIndexOf(":") + 1)));

        String str = "2222\"3456\"68669";
        int pre = str.indexOf("\"");
        System.out.println(pre);
        int suf = str.indexOf("\"", pre+1);
        System.out.println(suf);
        assertEquals("3456", str.substring(pre + 1, suf));
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void substring_error() {
        //substring 截取 [begin,end)
        assertEquals("3456789", "123456789".substring(2,111));
    }


    @Test
    public void replaceAll(){
        assertEquals("wei*jin234", "wei232123jin234".replaceAll("\\d{4,}", "*"));

        assertEquals("我我要要吃吃饭", "我...我...要..要.吃...吃...饭".replaceAll("\\.+", ""));

        assertEquals("我.我.要.要.吃.吃.饭", "我...我...要..要.吃...吃...饭".replaceAll("(.)\\1+", "$1"));
    }

    @Test
    public void match() {
        assertTrue("123433".matches("\\d+"));
    }
}
