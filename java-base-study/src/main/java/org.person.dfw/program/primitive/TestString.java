
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

/**
 * @moudle: TestString 
 * @version:v1.0
 *
 */
public class TestString {
    public static final String utf_8 = "utf-8";
    public static final String utf_16 = "utf-16";
    public static final String iso_8859_1 = "iso-8859-1";
    
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
    
    @Test public void codePointAt(){
        char[] c = {'a', 'b', '测', '试'};  
        System.out.println(Character.codePointAt(c, 0));//97  
        System.out.println(Character.codePointAt(c, 1));//98  
        System.out.println(Character.codePointAt(c, 2));//27979  
        System.out.println(Character.codePointAt(c, 3));//35797  
        System.out.println((char) 97);//a  
        System.out.println((char) 98);//b  
        System.out.println((char) 27979);//测  
        System.out.println((char) 35797);//试  
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
    
    @Test public void getBytes() throws UnsupportedEncodingException{
       String str = "中国ABC";
       System.out.println(str+"-默认编码:"+Arrays.toString(str.getBytes())+"---默认解码："+new String(str.getBytes()));
       System.out.println(str+"-utf-8编码:"+Arrays.toString(str.getBytes(utf_8))+"---对应解码："+new String(str.getBytes(utf_8),utf_8));
       System.out.println(str+"-utf-16编码:"+Arrays.toString(str.getBytes(utf_16))+"---对应解码："+new String(str.getBytes(utf_16),utf_16));
       System.out.println(str+"-iso-8859-1编码:"+Arrays.toString(str.getBytes(iso_8859_1))+"---对应解码："+new String(str.getBytes(iso_8859_1),iso_8859_1));
    }
    
    @Test public void split(){
    	System.out.println(Arrays.toString("aaa|bbb|ccc".split("|")));
    	//【注意】转义字符：字符"|","*","+","$"都得加上转义字符，前面加上"\\"
    	System.out.println(Arrays.toString("aaa|bbb|ccc".split("\\|")));
    	//而如果是"\"，那么就得写成"\\\\"。
    	System.out.println(Arrays.toString("aaa\\bbb\\ccc".split("\\\\")));
    	//有多个分割符时,可以用"|"作为连字符。
    	System.out.println(Arrays.toString("aaa|bbb|c$cc".split("\\||\\$")));
    }
    
    @Test public void indexof(){
    	//1、int indexOf(String str) ：返回第一次出现的指定子字符串在此字符串中的索引。 
    	//2、int indexOf(String str, int startIndex)：从指定的索引处开始，返回第一次出现的指定子字符串在此字符串中的索引。 
    	//3、int lastIndexOf(String str) ：返回在此字符串中最右边出现的指定子字符串的索引。 
    	//4、int lastIndexOf(String str, int startIndex) ：从指定的索引处开始向后搜索，返回在此字符串中最后一次出现的指定子字符串的索引。
    	
    	System.out.println("123456789".indexOf("1")); // 0
    	System.out.println("123456789".indexOf("a")); // -1
    	System.out.println("123456789".indexOf("23")); // 1
    	
    	System.out.println("12345656789".indexOf("56")); // 4
    	System.out.println("12345656789".indexOf("56", 4)); //4
    	System.out.println("12345656789".indexOf("56", 5)); //6
    	
    }
    
    @Test public void substring(){
    	//substring 截取 [begin,end)
    	System.out.println("123456789".substring(2));//3456789
    	System.out.println("123456789".substring(0, 3));//123
    	
    	//常用按照分割符号来获取字符串   常用方式
    	System.out.println("123456:789".substring(0, "123456:789".indexOf(":")));//123456
    	System.out.println("123456:7:89".substring("123456:7:89".lastIndexOf(":")+1));//89

        String str = "2222\"3456\"68669";
        int pre = str.indexOf("\"");
        System.out.println(pre);
        int suf = str.indexOf("\"", pre+1);
        System.out.println(suf);
        System.out.println(str.substring(pre+1,suf));
    }
    
    @Test  public void constructor() {
    	//返回一个特定的子数组的char数组参数的字符串表示形式(offset,offset+length]。2是起始位置，3是长度
		System.out.println(new String(new char[]{'a','b','c','d','e','f'},2,3));
	}

    @Test public void replace(){
    	String str="wei232123jin234";
    	String ragex = "\\d{4,}";    
    	String newstr = "*";    
    	String s =str.replaceAll(ragex, newstr);    
    	System.out.println(s); 
    	String str2 = "我...我...要..要.吃...吃...饭";   
    	String regex = "\\.+";   
    	String newStr = "";   
    	String str3 = str2.replaceAll(regex, newStr);   
    	System.out.println(str3);
    	regex = "(.)\\1+";   
    	newStr = "$1"; 
    	String str4 = str3.replaceAll(regex, newStr);   
    	System.out.println(str4);
    }
}
