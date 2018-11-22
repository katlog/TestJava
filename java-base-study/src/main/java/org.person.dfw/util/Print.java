package org.person.dfw.util;

import java.util.List;



/**
 * @moudle: Print 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月2日 下午2:29:08
 *
 */
public interface Print {
	
	/** long二进制长度 */
	public static final int LONG_BIN_LEN = 64;
	
	/** int二进制长度 */
	public static final int INT_BIN_LEN = 32;

	/** short二进制长度 */
	public static final int SHORT_BIN_LEN = 16;

	/** byte二进制长度 */
	public static final int BYTE_BIN_LEN = 8;

	public enum Style{
		Vertical,Horizontal;
	}
	
	public static  void format(Style style, String filterReg, List list) {
		System.out.println("Print interface format");
	}
	
	public class AAA{
		
	}
	
	public static void segment(int l) {
		for (int i = 0; i < l; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	/**
	 * 将long类型格式化为二进制，高位为0的那种
	 */
	public static void bin(long i){
		StringBuffer string = new StringBuffer(String.valueOf(Long.toBinaryString(i)));
		while (string.length()<LONG_BIN_LEN) {
			string.insert(0, "0");
		}
		forDivi(string);
	}
	
    /**
     * 将int类型格式化为二进制，高位为0的那种
     */
	public static void bin(int i){
        StringBuffer string = new StringBuffer(String.valueOf(Integer.toBinaryString(i)));
        while (string.length()<INT_BIN_LEN) {
            string.insert(0, "0");
        }
        forDivi(string);
    }
	
	/**
	 * 将short类型格式化为二进制，高位为0的那种
	 */
	public static void bin(short i){
		StringBuffer string = new StringBuffer(Integer.toBinaryString((i & 0xFFFF) + 0x10000).substring(1));
		while (string.length()<SHORT_BIN_LEN) {
			string.insert(0, "0");
		}
		forDivi(string);
	}
	
	/**
	 * 将byte类型格式化为二进制，高位为0的那种
	 */
	public static void bin(byte i){
		StringBuffer string = new StringBuffer(Integer.toBinaryString((i & 0xFF) + 0x100).substring(1));
		while (string.length()<BYTE_BIN_LEN) {
			string.insert(0, "0");
		}
		forDivi(string);
	}

	/**
	 * 四位分割
	 */ 
	public static void forDivi(StringBuffer string) {
		int length = string.length();
		for (int j = 4; j < length+3; j+=5) {
			string.insert(j, new char[]{' '}, 0, 1);
		}
		System.out.println(string);
	}
	
}
