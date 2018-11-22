/**
 * 2009-5-8
 */
package encryptDecrypt.zlex.chapter07_5PEB;

import static org.junit.Assert.*;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

/**
 * PBE校验
 * 
 * @author 梁栋
 * @version 1.0
 */
public class PBECoderTest {

	static class PBECoder {
		/**
		 * Java 6 支持以下任意一种算法
		 * 
		 * <pre>
		 * PBEWithMD5AndDES 
		 * PBEWithMD5AndTripleDES 
		 * PBEWithSHA1AndDESede
		 * PBEWithSHA1AndRC2_40
		 * </pre>
		 */
		public static final String ALGORITHM = "PBEWithMD5AndTripleDES";

		/**
		 * 盐初始化<br>
		 * 盐长度必须为8字节
		 * 
		 * @return byte[] 盐
		 * @throws Exception
		 */
		public static byte[] initSalt() throws Exception {

			SecureRandom random = new SecureRandom();

			return random.generateSeed(8);
		}

		/**
		 * 转换密钥
		 * 
		 * @param password
		 *            密码
		 * @return Key 密钥
		 * @throws Exception
		 */
		private static Key toKey(String password) throws Exception {

			// 密钥材料转换
			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());

			// 实例化
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);

			// 生成密钥
			SecretKey secretKey = keyFactory.generateSecret(keySpec);

			return secretKey;
		}

		/**
		 * 加密
		 * 
		 * @param data
		 *            数据
		 * @param password
		 *            密码
		 * @param salt
		 *            盐
		 * @return byte[] 加密数据
		 * @throws Exception
		 */
		public static byte[] encrypt(byte[] data, String password, byte[] salt)
				throws Exception {

			// 转换密钥
			Key key = toKey(password);

			// 实例化PBE参数材料
			PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);

			// 实例化
			Cipher cipher = Cipher.getInstance(ALGORITHM);

			// 初始化
			cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

			// 执行操作
			return cipher.doFinal(data);

		}

		/**
		 * 解密
		 * 
		 * @param data
		 *            数据
		 * @param password
		 *            密码
		 * @param salt
		 *            盐
		 * @return byte[] 解密数据
		 * @throws Exception
		 */
		public static byte[] decrypt(byte[] data, String password, byte[] salt)
				throws Exception {

			// 转换密钥
			Key key = toKey(password);

			// 实例化PBE参数材料
			PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);

			// 实例化
			Cipher cipher = Cipher.getInstance(ALGORITHM);

			// 初始化
			cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

			// 执行操作
			return cipher.doFinal(data);

		}
	}
	@Test
	public void test() throws Exception {

		String inputStr = "PBE";
		System.err.println("原文：" + inputStr);
		byte[] input = inputStr.getBytes();

		String pwd = "snowolf@zlex.org";
		System.err.println("密码：\t" + pwd);

		// 初始化盐
		byte[] salt = PBECoder.initSalt();
		System.err.println("盐：\t" + Base64.encodeBase64String(salt));

		// 加密
		byte[] data = PBECoder.encrypt(input, pwd, salt);
		System.err.println("加密后：\t" + Base64.encodeBase64String(data));
		
		// 解密
		byte[] output = PBECoder.decrypt(data, pwd, salt);
		String outputStr = new String(output);
		System.err.println("解密后：\t" + outputStr);
		
		// 校验
		assertEquals(inputStr, outputStr);
	}

}
