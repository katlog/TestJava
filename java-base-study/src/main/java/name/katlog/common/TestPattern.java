
/**  
 * @Title: TestPattern.java
 * @Package: org.person.dfw.common
 * @author: katlog
 * @date: 2017年4月21日 上午10:14:36
 * @version: V1.0  
 */ 
package name.katlog.common;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * pattern正则类
 */
public class TestPattern {
    @Test public void pattern(){
        /**
         * 构造方法：Pattern complie(String regex) 
         *       由于Pattern的构造函数是私有的,不可直接创建,故通过静态方法compile(String regex)方法来创建,
         *       将给定的正则表达式编译并赋予给Pattern类
         * 构造方法：Pattern compile(String regex, int flags)
         *         方法功能和compile(String regex)相同,不过增加了flag参数
         *         flag参数用来控制正则表达式的匹配行为
         *   flag参数：
         *         Pattern.CANON_EQ 当且仅当两个字符的”正规分解(canonical decomposition)”都完全相同的情况下，
         *              才认定匹配.比如用了这个标志之后，表达式”a\u030A”会匹配”?”.默认情况下，不考虑”规范相等性(canonical equivalence)”.
         *         Pattern.CASE_INSENSITIVE(?i) 默认情况下,大小写不明感的匹配只适用于US-ASCII字符集.这个标志能让表达式忽略大小写进行匹配.要想对
         *              Unicode字符进行大小不明感的匹 配,只要将UNICODE_CASE与这个标志合起来就行了.
         *         Pattern.COMMENTS(?x) 在这种模式下,匹配时会忽略(正则表达式里的)空格字符(译者注:不是指表达式里的”\s”，而是指表达式里的空格,tab,回
         *              车之类).注释从#开始,一直到这行结束.可以通过嵌入式的标志来启用Unix行模式.
         *         Pattern.DOTALL(?s)在这种模式下，表达式’.’可以匹配任意字符，包括表示一行的结束符。默认情况下，表达式’.’不匹配行的结束符.
         *         Pattern.MULTILINE(?m)在这种模式下,’\^’和’$’分别匹配一行的开始和结束.此外,’^’仍然匹配字符串的开始,’$’也匹配字符串的结束.默
         *              认情况下,这两个表达式仅仅匹配字符串的开始和结束.
         *         Pattern.UNICODE_CASE(?u) 在这个模式下,如果你还启用了CASE_INSENSITIVE标志,那么它会对Unicode字符进行大小写不明感的匹配.默认情况
         *              下,大小写不敏感的匹配只适用于US-ASCII字符集.
         *         Pattern.UNIX_LINES(?d) 在这个模式下,只有’\n’才被认作一行的中止,并且与’.’,’^’,以及’$’进行匹配.
         */
        Pattern pattern = Pattern.compile("\\?{2}");
        Pattern pattern1 = Pattern.compile("\\?{2}",Pattern.CASE_INSENSITIVE); //忽略大小写
        /**
         * 2.1、String pattern()
         *      返回正则表达式的字符串形式,其实就是返回Pattern.complile(String regex)的regex参数
         * 2.2、int flags() 返回当前Pattern的匹配flag参数. 
         */
        assertEquals(pattern.pattern(), "\\?{2}");

        /**boolean matches() 最常用方法:尝试对整个目标字符展开匹配检测,也就是只有整个目标字符串完全匹配时才返回真值.*/
        Matcher matcher = pattern.matcher("??");
        assertTrue(matcher.matches());
        
        /**public static boolean matches(String regex,CharSequence input)给定正则表达式并尝试将给定输入与其匹配。*/
        assertTrue(Pattern.matches("a*b", "aaaaab"));
        matcher=pattern.matcher("?");
        assertFalse(matcher.matches());
    }

    @Test
    public void split(){
        /**
         *  String[] splitBatchFun(CharSequence input) 和String[] splitBatchFun(CharSequence input, int limit)
         *      limit：结果阈值；
         *          如果 n 大于零，那么模式至多应用 n- 1 次，数组的长度不大于 n，并且数组的最后条目将包含除最后的匹配定界符之外的所有输入
         *          如果 n 非正，那么将应用模式的次数不受限制，并且数组可以为任意长度。
         *          如果 n 为零，那么应用模式的次数不受限制，数组可以为任意长度，并且将丢弃尾部空字符串。
         *          例子：若input="boo:and:foo"，匹配符为"o"，可知模式最多可应用4次，数组的长度最大为5；
         */
        CharSequence input = "boo:and:foo";
        Pattern p = Pattern.compile("o");
        assertArrayEquals(p.split(input, -2),new String[]{"b","",":and:f","",""});
        assertArrayEquals(p.split(input, 2),new String[]{"b","o:and:foo"});
        assertArrayEquals(p.split(input, 7),new String[]{"b","",":and:f","",""});
        assertArrayEquals(p.split(input, 0),new String[]{"b","",":and:f"});
    }

    @Test
    /** 返回给定的字符串的 匹配模式字面量 */
    public void quote(){
        String pattern2 = Pattern.quote("1252343% 8 567 hdfg gf^$545");
        assertEquals(pattern2, "\\Q1252343% 8 567 hdfg gf^$545\\E");
    }

    @Test
    public void lookingAt(){
        /**boolean lookingAt() 对前面的字符串进行匹配,只有匹配到的字符串在最前面才会返回true*/
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("22bb23");
        assertTrue(m.lookingAt());

        m = p.matcher("bb2233");
        assertFalse(m.lookingAt());
    }

    @Test
    public void find(){
        /**boolean find() 对字符串进行匹配,匹配到的字符串可以在任何位置*/
        Pattern p1 = Pattern.compile("\\d+");
        Matcher m1 = p1.matcher("22bb23");
        assertTrue(m1.find());

        Matcher m2 = p1.matcher("aa2223");
        assertTrue(m2.find());

        Matcher m3 = p1.matcher("aa2223bb");
        assertTrue(m3.find());

        Matcher m4 = p1.matcher("aabb");
        assertFalse(m4.find());
    }

    @Test public void find_start_group(){

        /**【先调用find后才能调用start、end、group】*/
        /**
         * int start() 返回当前匹配到的字符串在原目标字符串中的位置
         * int end() 返回当前匹配的字符串的最后一个字符在原目标字符串中的索引位置.
         * String group() 返回匹配到的子字符串
         */
        Pattern p1 = Pattern.compile("\\d+");
        Matcher m5 = p1.matcher("aa22bb23");
        m5.find();
        assertEquals(2,m5.start());
        assertEquals("22",m5.group());
        assertEquals(4,m5.end());

        Matcher m7 = p1.matcher("aa1bb12ccc123");
        byte i = 0;
        while (m7.find()) {
        	System.out.printf("第%s匹配    匹配到的字符串:%-10s匹配到字符串的位置:%s\n",
        			++i,m7.group(),m7.start());
		}
        System.out.printf("匹配到的次数\t%s",i);

    }
    
    @Test public void groupCount(){
        String str = "15812345131s15812345672a15812345133s15812345674a" +
                "15812345135s15812345676as13312345644";
 
        String regex = "(1[3458][0-9]{9}).*(1[3458][0-9]{9}).*" +
                       "(1[3458][0-9]{9}).*(1[3458][0-9]{9}).*" +
                       "(1[3458][0-9]{9}).*(1[3458][0-9]{9}).*" +
                       "(1[3458][0-9]{9}).*";
 
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        int count = matcher.groupCount();
        System.out.println("groupCount: " + count);
        while (matcher.find()) {
        	System.out.println("匹配的全部"+matcher.group());
            for (int i1 = 1; i1 <= count; i1++) {
//                if (matcher.start(i1) > -1) {//好像这个判断没什么用
                	//group(i)捕获的第i个：正则中用()进行捕获
                    System.out.println("group " + i1 + ": " + matcher.group(i1));
//                }
            }
        }
    	
    }
    
}
