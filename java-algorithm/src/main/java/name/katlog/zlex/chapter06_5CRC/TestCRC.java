/**
 * 2009-9-13
 */
package name.katlog.zlex.chapter06_5CRC;

import java.util.zip.CRC32;

import org.junit.Test;

import sun.misc.CRC16;

/**
 * 测试CRC-32
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class TestCRC {
	/**
	 * 测试CRC-32
	 * 
	 * @throws Exception
	 */
	@Test public void testCRC32() throws Exception {
		
		String str = "测试CRC-32";
		CRC32 crc32 = new CRC32();
		crc32.update(str.getBytes());
		
		String hex = Long.toHexString(crc32.getValue());
		System.err.println("原文：\t" + str);
		System.err.println("CRC-32：\t" + hex);
	}
	
/*	@Test public void testCRC16() throws Exception {
		
		String str = "测试CRC-32";
		CRC16 crc16 = new CRC16();
		
		for (byte b : str.getBytes()) {
			crc16.update(b);
		}
		
		String hex = Long.toHexString(crc16.);
		System.err.println("原文：\t" + str);
		System.err.println("CRC-32：\t" + hex);
	}*/
	

}
