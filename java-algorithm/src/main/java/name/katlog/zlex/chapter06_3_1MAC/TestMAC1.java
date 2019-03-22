/**
 * 2009-9-11
 */
package name.katlog.zlex.chapter06_3_1MAC;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

/**
 * MAC校验
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class TestMAC1 {
	
	private static final String str = "MAC消息摘要";
	private static final byte[] input = str.getBytes();
	
	private static KeyGenerator hmacMD5KeyGenerator;
	private static SecretKey hmacMD5SecretKey;
	private static Mac hmacMD5mac;
	
	private static KeyGenerator HMacTigerKeyGenerator;
	private static SecretKey HMacTigerSecretKey;
	private static Mac HMacTigermac;
	
	private static KeyGenerator HmacSHA256KeyGenerator;
	private static SecretKey HmacSHA256SecretKey;
	private static Mac HmacSHA256mac;
	
	private static KeyGenerator HmacSHA384KeyGenerator;
	private static SecretKey HmacSHA384SecretKey;
	private static Mac HmacSHA384mac;
	
	private static KeyGenerator HmacSHA512KeyGenerator;
	private static SecretKey HmacSHA512SecretKey;
	private static Mac HmacSHA512mac;
	
	static{
		try {
			//HmacMD5
			hmacMD5KeyGenerator =KeyGenerator.getInstance("HmacMD5"); //初始化KeyGenerator
			hmacMD5SecretKey = hmacMD5KeyGenerator.generateKey(); //另一种创建还原方式
																//获得密钥byte[] key = secretKey.getEncoded();
																//SecretKey secretKey = new SecretKeySpec(key, "HmacMD5");
			hmacMD5mac = Mac.getInstance("SslMacMD5");
			hmacMD5mac.init(hmacMD5SecretKey);
			
			//HMacTiger 【有问题】
//			HMacTigerKeyGenerator =KeyGenerator.getInstance("HMacTiger");
//			HMacTigerSecretKey = hmacMD5KeyGenerator.generateKey(); 
//			HMacTigermac = Mac.getInstance("SslMacMD5");
//			HMacTigermac.init(HMacTigerSecretKey);
			
			//HmacSHA256
			HmacSHA256KeyGenerator =KeyGenerator.getInstance("HmacSHA256");
			HmacSHA256SecretKey = HmacSHA256KeyGenerator.generateKey(); 
			HmacSHA256mac = Mac.getInstance(HmacSHA256SecretKey.getAlgorithm()); //【不同】
			HmacSHA256mac.init(HmacSHA256SecretKey);
			
			//HmacSHA384
			HmacSHA384KeyGenerator =KeyGenerator.getInstance("HmacSHA384");
			HmacSHA384SecretKey = HmacSHA384KeyGenerator.generateKey(); 
			HmacSHA384mac = Mac.getInstance(HmacSHA384SecretKey.getAlgorithm());
			HmacSHA384mac.init(HmacSHA384SecretKey);
			
			//HmacSHA512SecretKey
			HmacSHA512KeyGenerator =KeyGenerator.getInstance("HmacSHA512");
			HmacSHA512SecretKey = HmacSHA512KeyGenerator.generateKey(); 
			HmacSHA512mac = Mac.getInstance(HmacSHA512SecretKey.getAlgorithm());
			HmacSHA512mac.init(HmacSHA512SecretKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test public void HmacMD5(){
		// 执行消息摘要
		byte[] output = hmacMD5mac.doFinal(input);
		System.out.println(Arrays.toString(output));
		System.out.printf(Hex.encodeHexString(output));
	}

	@Test public void HMacTiger(){//【有问题】
		// 执行消息摘要
		byte[] output = HMacTigermac.doFinal(input);
		System.out.println(Arrays.toString(output));
	}

	@Test public void HmacSHA256(){
		// 执行消息摘要
		byte[] output = HmacSHA256mac.doFinal(input);
		System.out.println(Arrays.toString(output));
	}

	@Test public void HmacSHA384(){
		// 执行消息摘要
		byte[] output = HmacSHA384mac.doFinal(input);
		System.out.println(Arrays.toString(output));
	}

	@Test public void HmacSHA512(){
		// 执行消息摘要
		byte[] output = HmacSHA512mac.doFinal(input);
		System.out.println(Arrays.toString(output));
	}
}
