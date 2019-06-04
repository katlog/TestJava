
/**  
 * @Title: TestCode.java
 * @Package: org.person.dfw.encode
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月21日 上午11:37:48
 * @version: V1.0  
 */ 
package org.person.dfw.program.primitive.code;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Arrays;

import org.junit.Test;


/**
 * @moudle: TestCode 
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月21日 上午11:37:48
 *
 */
public class TestCode {
    private final static String ascii = "ascii";
    private final static String uft_8 = "UTF-8";  //注意UTF要大写
    private final static String uft_16 = "UTF-16"; //注意UTF要大写
    private final static String iso_8859_1="iso-8859-1";
    private final static String gbk = "GBK";
    private final static String gb2312 = "gb2312";
    
    private final static String str = "I am 君山";
    private final static String str_cn = "中文";
    
    
    @Test
    public void toBytes(){
        printByte(str);
        
        printByte(str_cn);
    }
    
    
    private void printByte(String str){
        try {
            System.out.println("原字符："+str);
            System.out.println(ascii+":"+ Arrays.toString(str.getBytes(ascii)));
            System.out.println(iso_8859_1+":"+ Arrays.toString(str.getBytes(iso_8859_1)));
            System.out.println(uft_8+":"+ Arrays.toString(str.getBytes(uft_8)));
            System.out.println(uft_16+":"+ Arrays.toString(str.getBytes(uft_16)));
            System.out.println(gbk+":"+ Arrays.toString(str.getBytes(gbk)));
            System.out.println(gb2312+":"+ Arrays.toString(str.getBytes(gb2312)));
            System.out.println("---------------------------------");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void iso(){
        String str = "úù§ABD";
        try {
            
            byte[] ch = str.getBytes(iso_8859_1);
            String asc = new String(ch, ascii);
            String iso = new String(ch, iso_8859_1);
            System.out.println("length:"+ch.length);
            System.out.println(Arrays.toString(ch));
            System.out.println("ASCII:"+asc +"\nISO-8859-1:"+iso);
            System.out.println("-------------------------");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void readFile(){
        
        try {
            URL resource = Thread.currentThread().getContextClassLoader().getResource("");
            InputStream is = new FileInputStream(resource.getPath()+"\\org\\person\\dfw\\encode\\text_utf8.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, uft_16));
            String row = null;
            while ((row=br.readLine())!=null) {
                System.out.println(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            
        }
    }
    
}
