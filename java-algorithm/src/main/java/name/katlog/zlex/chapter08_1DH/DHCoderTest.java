/**
 * 2009-5-7
 */
package name.katlog.zlex.chapter08_1DH;

import static org.junit.Assert.*;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

/**
 * DH校验
 * 
 * @author 梁栋
 * @version 1.0
 */
public class DHCoderTest {

	static class DHCoder {

		/**
		 * 非对称加密密钥算法
		 */
		public static final String KEY_ALGORITHM = "DH";

		/**
		 * 本地密钥算法，即对称加密密钥算法，可选DES、DESede和AES算法
		 */
		public static final String SECRET_KEY_ALGORITHM = "AES";

		/**
		 * 默认密钥长度
		 * 
		 * DH算法默认密钥长度为1024 密钥长度必须是64的倍数，其范围在512到1024位之间。
		 */
		private static final int KEY_SIZE = 512;

		/**
		 * 公钥
		 */
		private static final String PUBLIC_KEY = "DHPublicKey";

		/**
		 * 私钥
		 */
		private static final String PRIVATE_KEY = "DHPrivateKey";

		/**
		 * 初始化甲方密钥
		 * 
		 * @return Map 甲方密钥Map
		 * @throws Exception
		 */
		public static Map<String, Object> initKey() throws Exception {

			// 实例化密钥对生成器
			KeyPairGenerator keyPairGenerator = KeyPairGenerator
					.getInstance(KEY_ALGORITHM);

			// 初始化密钥对生成器
			keyPairGenerator.initialize(KEY_SIZE);

			// 生成密钥对
			KeyPair keyPair = keyPairGenerator.generateKeyPair();

			// 甲方公钥
			DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();

			// 甲方私钥
			DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();

			// 将密钥对存储在Map中
			Map<String, Object> keyMap = new HashMap<String, Object>(2);

			keyMap.put(PUBLIC_KEY, publicKey);
			keyMap.put(PRIVATE_KEY, privateKey);

			return keyMap;
		}

		/**
		 * 初始化乙方密钥
		 * 
		 * @param key
		 *            甲方公钥
		 * @return Map 乙方密钥Map
		 * @throws Exception
		 */
		public static Map<String, Object> initKey(byte[] key) throws Exception {

			// 解析甲方公钥
			// 转换公钥材料
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

			// 实例化密钥工厂
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

			// 产生公钥
			PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

			// 由甲方公钥构建乙方密钥
			DHParameterSpec dhParamSpec = ((DHPublicKey) pubKey).getParams();

			// 实例化密钥对生成器
			KeyPairGenerator keyPairGenerator = KeyPairGenerator
					.getInstance(keyFactory.getAlgorithm());

			// 初始化密钥对生成器
			keyPairGenerator.initialize(dhParamSpec);

			// 产生密钥对
			KeyPair keyPair = keyPairGenerator.genKeyPair();

			// 乙方公钥
			DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();

			// 乙方私钥
			DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();

			// 将密钥对存储在Map中
			Map<String, Object> keyMap = new HashMap<String, Object>(2);

			keyMap.put(PUBLIC_KEY, publicKey);
			keyMap.put(PRIVATE_KEY, privateKey);

			return keyMap;
		}

		/**
		 * 加密
		 * 
		 * @param data
		 *            待加密数据
		 * @param key
		 *            密钥
		 * @return byte[] 加密数据
		 * @throws Exception
		 */
		public static byte[] encrypt(byte[] data, byte[] key) throws Exception {

			// 生成本地密钥
			SecretKey secretKey = new SecretKeySpec(key, SECRET_KEY_ALGORITHM);

			// 数据加密
			Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());

			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			return cipher.doFinal(data);
		}

		/**
		 * 解密<br>
		 * 
		 * @param data
		 *            待解密数据
		 * @param key
		 *            密钥
		 * @return byte[] 解密数据
		 * @throws Exception
		 */
		public static byte[] decrypt(byte[] data, byte[] key) throws Exception {

			// 生成本地密钥
			SecretKey secretKey = new SecretKeySpec(key, SECRET_KEY_ALGORITHM);

			// 数据解密
			Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());

			cipher.init(Cipher.DECRYPT_MODE, secretKey);

			return cipher.doFinal(data);
		}

		/**
		 * 构建密钥
		 * 
		 * @param publicKey
		 *            公钥
		 * @param privateKey
		 *            私钥
		 * @return byte[] 本地密钥
		 * @throws Exception
		 */
		public static byte[] getSecretKey(byte[] publicKey, byte[] privateKey)
				throws Exception {

			// 实例化密钥工厂
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

			// 初始化公钥
			// 密钥材料转换
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);

			// 产生公钥
			PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

			// 初始化私钥
			// 密钥材料转换
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);

			// 产生私钥
			PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

			// 实例化
			KeyAgreement keyAgree = KeyAgreement.getInstance(keyFactory
					.getAlgorithm());

			// 初始化
			keyAgree.init(priKey);

			keyAgree.doPhase(pubKey, true);

			// 生成本地密钥
			SecretKey secretKey = keyAgree.generateSecret(SECRET_KEY_ALGORITHM);

			return secretKey.getEncoded();
		}

		/**
		 * 取得私钥
		 * 
		 * @param keyMap
		 *            密钥Map
		 * @return byte[] 私钥
		 * @throws Exception
		 */
		public static byte[] getPrivateKey(Map<String, Object> keyMap)
				throws Exception {

			Key key = (Key) keyMap.get(PRIVATE_KEY);

			return key.getEncoded();
		}

		/**
		 * 取得公钥
		 * 
		 * @param keyMap
		 *            密钥Map
		 * @return byte[] 公钥
		 * @throws Exception
		 */
		public static byte[] getPublicKey(Map<String, Object> keyMap)
				throws Exception {

			Key key = (Key) keyMap.get(PUBLIC_KEY);

			return key.getEncoded();
		}
	}
	/**
	 * 甲方公钥
	 */
	private byte[] publicKey1;
	/**
	 * 甲方私钥
	 */
	private byte[] privateKey1;

	/**
	 * 甲方本地密钥
	 */
	private byte[] key1;

	/**
	 * 乙方公钥
	 */
	private byte[] publicKey2;

	/**
	 * 乙方私钥
	 */
	private byte[] privateKey2;

	/**
	 * 乙方本地密钥
	 */
	private byte[] key2;

	/**
	 * 初始化密钥
	 * 
	 * @throws Exception
	 */
	@Before
	public final void initKey() throws Exception {

		// 生成甲方密钥对儿
		Map<String, Object> keyMap1 = DHCoder.initKey();

		publicKey1 = DHCoder.getPublicKey(keyMap1);
		privateKey1 = DHCoder.getPrivateKey(keyMap1);

		System.err.println("甲方公钥:\n" + Base64.encodeBase64String(publicKey1));
		System.err.println("甲方私钥:\n" + Base64.encodeBase64String(privateKey1));

		// 由甲方公钥产生本地密钥对儿
		Map<String, Object> keyMap2 = DHCoder.initKey(publicKey1);

		publicKey2 = DHCoder.getPublicKey(keyMap2);
		privateKey2 = DHCoder.getPrivateKey(keyMap2);

		System.err.println("乙方公钥:\n" + Base64.encodeBase64String(publicKey2));
		System.err.println("乙方私钥:\n" + Base64.encodeBase64String(privateKey2));

		key1 = DHCoder.getSecretKey(publicKey2, privateKey1);
		System.err.println("甲方本地密钥:\n" + Base64.encodeBase64String(key1));

		key2 = DHCoder.getSecretKey(publicKey1, privateKey2);
		System.err.println("乙方本地密钥:\n" + Base64.encodeBase64String(key2));

		assertArrayEquals(key1, key2);
	}

	/**
	 * 校验
	 * 
	 * @throws Exception
	 */
	@Test
	public final void test() throws Exception {

		System.err.println("\n=====甲方向乙方发送加密数据=====");
		String input1 = "密码交换算法 ";
		System.err.println("原文: " + input1);

		System.err.println("---使用甲方本地密钥对数据加密---");

		// 使用甲方本地密钥对数据加密
		byte[] code1 = DHCoder.encrypt(input1.getBytes(), key1);

		System.err.println("加密: " + Base64.encodeBase64String(code1));

		System.err.println("---使用乙方本地密钥对数据解密---");

		// 使用乙方本地密钥对数据解密
		byte[] decode1 = DHCoder.decrypt(code1, key2);
		String output1 = (new String(decode1));

		System.err.println("解密: " + output1);

		assertEquals(input1, output1);

		System.err.println("\n=====乙方向甲方发送加密数据=====");
		String input2 = "DH";
		System.err.println("原文: " + input2);

		System.err.println("---使用乙方本地密钥对数据加密---");

		// 使用乙方本地密钥对数据加密
		byte[] code2 = DHCoder.encrypt(input2.getBytes(), key2);

		System.err.println("加密: " + Base64.encodeBase64String(code2));

		System.err.println("---使用甲方本地密钥对数据解密---");

		// 使用甲方本地密钥对数据解密
		byte[] decode2 = DHCoder.decrypt(code2, key1);
		String output2 = (new String(decode2));

		System.err.println("解密: " + output2);

		// 校验
		assertEquals(input2, output2);
	}
}
