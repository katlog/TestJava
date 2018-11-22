/**
 * 2008-6-11
 */
package encryptDecrypt.zlex.chapter08_2RSA;

import static org.junit.Assert.*;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * RSA校验
 * 
 * @author 梁栋
 * @version 1.0
 */
public class RSACoderTest {
	private static final String str = "非对称加密算法";
	
	private static final byte[] input = str.getBytes();
	
	
	/** 非对称加密密钥算法*/
	public static final String KEY_RSA = "RSA";
	
	/**公钥*/
	private static final String PUBLIC_KEY = "RSAPublicKey";
	
	/**私钥*/
	private static final String PRIVATE_KEY = "RSAPrivateKey";
	
	/**公钥*/
	private byte[] publicKeyByte;

	/**私钥*/
	private byte[] privateKeyByte;

	
	/**
	 * RSA密钥长度 默认1024位， 密钥长度必须是64的倍数， 范围在512至65536位之间。
	 */
	private static final int KEY_SIZE = 512;
	
	
	@Test public void RSA() throws NoSuchAlgorithmException, InvalidKeySpecException, 
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		
		//1、初始化秘钥
		// 实例化密钥对生成器
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_RSA);
		// 初始化密钥对生成器
		keyPairGen.initialize(KEY_SIZE);
		// 生成密钥对
		KeyPair keyPair = keyPairGen.generateKeyPair();
		
		// 公钥
		RSAPublicKey RSAPublicKey = (RSAPublicKey) keyPair.getPublic();
		// 私钥
		RSAPrivateKey RSAPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
		//获取秘钥的byte类型
		publicKeyByte = ((Key)RSAPublicKey).getEncoded();
		privateKeyByte = ((Key)RSAPrivateKey).getEncoded();
		System.out.println("公钥: \t" + Base64.encodeBase64String(publicKeyByte));
		System.out.println("私钥： \t" + Base64.encodeBase64String(privateKeyByte));
		
		//2、私钥加密——公钥解密
		System.out.println("\n---私钥加密——公钥解密---");
		System.out.println("原文:\t\t" + str);

		//2.1获取私钥并加密
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKeyByte);
		KeyFactory keyFactory1 = KeyFactory.getInstance(KEY_RSA);
		// 生成私钥
		PrivateKey privateKey = keyFactory1.generatePrivate(pkcs8KeySpec);
		
		// 对数据加密
		Cipher cipher1 = Cipher.getInstance(keyFactory1.getAlgorithm());
		cipher1.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] privateEncryptByte = cipher1.doFinal(input);
		System.out.println("加密后:\t" + Base64.encodeBase64String(privateEncryptByte));
		
		//2.2获取公钥并解密
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyByte);
		KeyFactory keyFactory2 = KeyFactory.getInstance(KEY_RSA);
		// 生成公钥
		PublicKey publicKey = keyFactory2.generatePublic(x509KeySpec);

		// 对数据解密
		Cipher cipher2 = Cipher.getInstance(keyFactory2.getAlgorithm());
		cipher2.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] publicDencryptByte = cipher1.doFinal(privateEncryptByte);
		System.out.println("解密后:\t" + new String(publicDencryptByte));
		
//		// 加密
//		byte[] encodedData1 = RSACoder.encryptByPrivateKey(input, privateKey);
//		System.err.println("加密后:\n" + Base64.encodeBase64String(encodedData1));
//
//		// 解密
//		byte[] decodedData1 = RSACoder.decryptByPublicKey(encodedData1,
//				RSAPublicKey);
//		String outputStr1 = new String(decodedData1);
//		System.err.println("解密后:\n" + outputStr1);
		
		
	}
	
	static class RSACoder {

		/**
		 * 私钥解密
		 */
		public static byte[] decryptByPrivateKey(byte[] data, byte[] key)
				throws Exception {

			// 取得私钥
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);

			KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA);

			// 生成私钥
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

			// 对数据解密
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

			cipher.init(Cipher.DECRYPT_MODE, privateKey);

			return cipher.doFinal(data);
		}

		/**
		 * 公钥解密
		 */
		public static byte[] decryptByPublicKey(byte[] data, byte[] key)
				throws Exception {

			// 取得公钥
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

			KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA);

			// 生成公钥
			PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

			// 对数据解密
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

			cipher.init(Cipher.DECRYPT_MODE, publicKey);

			return cipher.doFinal(data);
		}

		/**
		 * 公钥加密
		 */
		public static byte[] encryptByPublicKey(byte[] data, byte[] key)
				throws Exception {

			// 取得公钥
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

			KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA);

			PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

			// 对数据加密
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

			cipher.init(Cipher.ENCRYPT_MODE, publicKey);

			return cipher.doFinal(data);
		}

		/**
		 * 私钥加密
		 */
		public static byte[] encryptByPrivateKey(byte[] data, byte[] key)
				throws Exception {

			// 取得私钥
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);

			KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA);

			// 生成私钥
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

			// 对数据加密
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

			cipher.init(Cipher.ENCRYPT_MODE, privateKey);

			return cipher.doFinal(data);
		}

		/**
		 * 取得私钥
		 */
		public static byte[] getPrivateKey(Map<String, Object> keyMap)
				throws Exception {

			Key key = (Key) keyMap.get(PRIVATE_KEY);

			return key.getEncoded();
		}

		/**
		 * 取得公钥
		 */
		public static byte[] getPublicKey(Map<String, Object> keyMap)
				throws Exception {

			Key key = (Key) keyMap.get(PUBLIC_KEY);

			return key.getEncoded();
		}

		/**
		 * 初始化密钥
		 */
		public static Map<String, Object> initKey() throws Exception {

			// 实例化密钥对生成器
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_RSA);
			// 初始化密钥对生成器
			keyPairGen.initialize(KEY_SIZE);
			// 生成密钥对
			KeyPair keyPair = keyPairGen.generateKeyPair();
			// 公钥
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			// 私钥
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			// 封装密钥
			Map<String, Object> keyMap = new HashMap<String, Object>(2);
			keyMap.put(PUBLIC_KEY, publicKey);
			keyMap.put(PRIVATE_KEY, privateKey);

			return keyMap;
		}
	}


	/**
	 * 初始化密钥
	 * 
	 * @throws Exception
	 */
	@Before
	public void initKey() throws Exception {

		// 初始化密钥
		Map<String, Object> keyMap = RSACoder.initKey();

		publicKeyByte = RSACoder.getPublicKey(keyMap);
		privateKeyByte = RSACoder.getPrivateKey(keyMap);

		System.err.println("公钥: \n" + Base64.encodeBase64String(publicKeyByte));
		System.err.println("私钥： \n" + Base64.encodeBase64String(privateKeyByte));
	}

	/**
	 * 校验
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {

		System.err.println("\n---私钥加密——公钥解密---");

		String inputStr1 = "RSA加密算法";
		byte[] data1 = inputStr1.getBytes();
		System.err.println("原文:\n" + inputStr1);

		// 加密
		byte[] encodedData1 = RSACoder.encryptByPrivateKey(data1, privateKeyByte);
		System.err.println("加密后:\n" + Base64.encodeBase64String(encodedData1));

		// 解密
		byte[] decodedData1 = RSACoder.decryptByPublicKey(encodedData1,publicKeyByte);
		String outputStr1 = new String(decodedData1);
		System.err.println("解密后:\n" + outputStr1);

		// 校验
		assertEquals(inputStr1, outputStr1);

		System.err.println("\n---公钥加密——私钥解密---");
		String inputStr2 = "RSA Encypt Algorithm";
		byte[] data2 = inputStr2.getBytes();
		System.err.println("原文:\n" + inputStr2);

		// 加密
		byte[] encodedData2 = RSACoder.encryptByPublicKey(data2, publicKeyByte);
		System.err.println("加密后:\n" + Base64.encodeBase64String(encodedData2));

		// 解密
		byte[] decodedData2 = RSACoder.decryptByPrivateKey(encodedData2,
				privateKeyByte);
		String outputStr2 = new String(decodedData2);
		System.err.println("解密后: " + outputStr2);

		// 校验
		assertEquals(inputStr2, outputStr2);
	}

}
