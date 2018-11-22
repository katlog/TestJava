/**
 * 2009-9-11
 */
package encryptDecrypt.zlex.chapter06_3_1MAC;

import static org.junit.Assert.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

/**
 * MAC校验
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class TestMAC2 {
	

	private static final String str = "MAC消息摘要";
	private static final byte[] input = str.getBytes();
	
	private static KeyGenerator HmacMD2KeyGenerator;
	private static SecretKey HmacMD2SecretKey;
	private static Mac HmacMD2mac;
	
	private static KeyGenerator HmacMD4KeyGenerator;
	private static SecretKey HmacMD4SecretKey;
	private static Mac HmacMD4mac;
	
	private static KeyGenerator HmacSHA224KeyGenerator;
	private static SecretKey HmacSHA224SecretKey;
	private static Mac HmacSHA224mac;
	
	private static KeyGenerator HmacRipeMD128KeyGenerator;
	private static SecretKey HmacRipeMD128SecretKey;
	private static Mac HmacRipeMD128mac;
	
	private static KeyGenerator HmacRipeMD160KeyGenerator;
	private static SecretKey HmacRipeMD160SecretKey;
	private static Mac HmacRipeMD160mac;
	static{
		try {
			// 加入BouncyCastleProvider支持
			Security.addProvider(new BouncyCastleProvider());
			
			//HmacMD2
			HmacMD2KeyGenerator =KeyGenerator.getInstance("HmacMD2");
			HmacMD2SecretKey = HmacMD2KeyGenerator.generateKey(); 
			HmacMD2mac = Mac.getInstance(HmacMD2SecretKey.getAlgorithm());
			HmacMD2mac.init(HmacMD2SecretKey);
			
			//HmacMD4
			HmacMD4KeyGenerator =KeyGenerator.getInstance("HmacMD4");
			HmacMD4SecretKey = HmacMD4KeyGenerator.generateKey(); 
			HmacMD4mac = Mac.getInstance(HmacMD4SecretKey.getAlgorithm());
			HmacMD4mac.init(HmacMD4SecretKey);
			
			//HmacSHA224
			HmacSHA224KeyGenerator =KeyGenerator.getInstance("HmacSHA224");
			HmacSHA224SecretKey = HmacSHA224KeyGenerator.generateKey(); 
			HmacSHA224mac = Mac.getInstance(HmacSHA224SecretKey.getAlgorithm());
			HmacSHA224mac.init(HmacSHA224SecretKey);
			
			//HmacRipeMD128
			HmacRipeMD128KeyGenerator =KeyGenerator.getInstance("HmacRipeMD128");
			HmacRipeMD128SecretKey = HmacRipeMD128KeyGenerator.generateKey(); 
			HmacRipeMD128mac = Mac.getInstance(HmacRipeMD128SecretKey.getAlgorithm());
			HmacRipeMD128mac.init(HmacRipeMD128SecretKey);
			
			//HmacRipeMD160
			HmacRipeMD160KeyGenerator =KeyGenerator.getInstance("HmacRipeMD160");
			HmacRipeMD160SecretKey = HmacRipeMD160KeyGenerator.generateKey(); 
			HmacRipeMD160mac = Mac.getInstance(HmacRipeMD160SecretKey.getAlgorithm());
			HmacRipeMD160mac.init(HmacRipeMD160SecretKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test public void HmacMD2(){
		// 执行消息摘要
		byte[] output = HmacMD2mac.doFinal(input);
		System.out.println(Arrays.toString(output));
	}
	
	@Test public void HmacMD4(){
		// 执行消息摘要
		byte[] output = HmacMD4mac.doFinal(input);
		System.out.println(Arrays.toString(output));
	}
	
	@Test public void HmacSHA224(){
		// 执行消息摘要
		byte[] output = HmacSHA224mac.doFinal(input);
		System.out.println(Arrays.toString(output));
	}
	
	@Test public void HmacRipeMD128(){
		// 执行消息摘要
		byte[] output = HmacRipeMD128mac.doFinal(input);
		System.out.println(Arrays.toString(output));
	}
	
	@Test public void HmacRipeMD160(){
		// 执行消息摘要
		byte[] output = HmacRipeMD160mac.doFinal(input);
		System.out.println(Arrays.toString(output));
	}
}
