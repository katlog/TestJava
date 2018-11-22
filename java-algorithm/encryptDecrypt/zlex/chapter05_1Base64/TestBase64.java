package encryptDecrypt.zlex.chapter05_1Base64;

import java.io.UnsupportedEncodingException;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.UrlBase64;
import org.junit.Test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Base64编码与解码测试类
 */
public class TestBase64 {
	
	/**字符编码*/
	public final static String ENCODING = "UTF-8";
	/**待编码、解码的字符串*/
	private final static String str = "Base64 编码";
	/**字符串的byte数组*/
	private static byte[] input = null;
	
	static{
		try {
			input = str.getBytes(ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * sun.misc.BASE64Encoder
	 */
	@Test public final void BASE64Encoder() throws Exception {
		System.out.println("sun.misc.BASE64Encoder的base64编码encodeBuffer、解码decodeBuffer");
		System.err.println("原文:\t" + str);

		
		// 进行Base64编码
		BASE64Encoder encoder = new BASE64Encoder(); 
		String code = encoder.encodeBuffer(input);
		System.err.println("编码后:\t" + code);
		
		// 进行Base64解码
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b2 = decoder.decodeBuffer(code);
		System.err.println("解码后:\t" + new String(b2,ENCODING));
	}
	
	/**
	 * org.bouncycastle.util.encoders.Base64,进行编码、解码
	 */
	@Test public final void Base641() throws Exception {
		System.out.println("org.bouncycastle.util.encoders.Base64的base64编码encode、解码decode");
		System.err.println("原文:\t" + str);

		// Base64编码
		byte[] data = Base64.encode(input);
		System.err.println("编码后:\t" + new String(data));

		// Base64解码
		byte[] output = Base64.decode(data);
		System.err.println("解码后:\t" + new String(output));
	}

	/**
	 * org.bouncycastle.util.encoders.UrlBase64,进行编码、解码
	 */
	@Test public final void UrlBase64() throws Exception {
		System.out.println(" org.bouncycastle.util.encoders.UrlBase64的base64编码encode、解码decode");
		System.err.println("原文:\t" + str);

		// Url Base64编码
		byte[] data = UrlBase64.encode(input);
		System.err.println("编码后:\t" + new String(data));

		// Url Base64解码
		byte[] output = UrlBase64.decode(data);
		System.err.println("解码后:\t" + new String(output));
	}
	
	/**
	 * {@link org.apache.commons.codec.binary.Base64#encode} 编码
	 * {@link org.apache.commons.codec.binary.Base64#decode} 解码
	 * 貌似多出了一个换行
	 */
	@Test public final void Base64_1() throws Exception {
		System.out.println("org.apache.commons.codec.binary.Base64的base64编码encode、解码decode");
		System.err.println("原文:\t" + str);

		org.apache.commons.codec.binary.Base64 base64 = 
				new org.apache.commons.codec.binary.Base64();

		// Base64编码
		byte[] data = base64.encode(input);
		System.err.println("编码后:\t" + new String(data));

		// Base64解码
		byte[] output = base64.decode(data);
		System.err.println("解码后:\t" + new String(output));
	}
	
	/**
	 * {@link org.apache.commons.codec.binary.Base64#encodeBase64} 编码
	 * {@link org.apache.commons.codec.binary.Base64#decodeBase64} 解码
	 */
	@Test public final void Base64_2() throws Exception {
		System.out.println("org.apache.commons.codec.binary.Base64的base64编码encodeBase64、解码decodeBase64");
		System.err.println("原文:\t" + str);

		// Base64编码
		byte[] data = org.apache.commons.codec.binary.Base64.encodeBase64(input);
		System.err.println("编码后:\t" + new String(data));

		// Base64解码
		byte[] output = org.apache.commons.codec.binary.Base64.decodeBase64(data);
		System.err.println("解码后:\t" + new String(output));

	}
	
	/**
	 * {@link org.apache.commons.codec.binary.Base64#encodeBase64} 编码
	 * {@link org.apache.commons.codec.binary.Base64#decodeBase64} 解码
	 */
	@Test public final void Base64_3() throws Exception {
		System.out.println("org.apache.commons.codec.binary.Base64的base64安全编码encodeBase64、解码decodeBase64");
		System.err.println("原文:\t" + str);
		
		// Base64编码,Base64安全编码 遵循RFC 2045实现
		byte[] data = org.apache.commons.codec.binary.Base64.encodeBase64(input,true);
		System.err.println("编码后:\t" + new String(data));
		
		// Base64解码
		byte[] output = org.apache.commons.codec.binary.Base64.decodeBase64(data);
		System.err.println("解码后:\t" + new String(output));
		
	}
	
	/**
	 * {@link org.apache.commons.codec.binary.Base64#encodeBase64URLSafe} 编码
	 * {@link org.apache.commons.codec.binary.Base64#decodeBase64} 解码
	 * 不会处理url中不安全的字符，所以没出现=符号
	 */
	@Test public final void Base64_4() throws Exception {
		System.out.println("org.apache.commons.codec.binary.Base64的base64编码encodeBase64URLSafe、解码decodeBase64");
		System.err.println("原文:\t" + str);

		// Base64编码
		byte[] data = org.apache.commons.codec.binary.Base64.encodeBase64URLSafe(input);
		System.err.println("编码后:\t" + new String(data));

		// Base64解码
		byte[] output = org.apache.commons.codec.binary.Base64.decodeBase64(data);
		System.err.println("解码后:\t" + new String(output));

	}
}
