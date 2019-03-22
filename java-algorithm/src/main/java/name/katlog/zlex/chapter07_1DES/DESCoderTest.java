/**
 * 2008-6-11
 */
package name.katlog.zlex.chapter07_1DES;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

/**
 * DES安全编码组件校验
 */
public class DESCoderTest {
	
	
	private static final String  str = "对称加密";
	private static final byte[] input = str.getBytes();

	/**
	 * 密钥算法 <br>
	 * Java 6 只支持56bit密钥 <br>
	 * Bouncy Castle 支持64bit密钥
	 */
	public static final String KEY_DES = "DES";
	
	/**
	 * 加密、解密算法DES / 工作模式 ECB/ 填充方式PKCS5PADDING
	 */
	public static final String CIPHER_DES = "DES/ECB/PKCS5PADDING";
	

	/**
	 * 密钥算法
	 */
	public static final String KEY_DESede = "DESede";

	/**
	 * 加密、解密算法 DESede/ 工作模式 ECB/ 填充方式PKCS5Padding
	 * Java 6支持PKCS5PADDING填充方式
	 * Bouncy Castle支持PKCS7Padding填充方式
	 */
	public static final String CIPHER_DESede = "DESede/ECB/PKCS5Padding";
	
	/**
	 * 密钥算法
	 */
	public static final String KEY_AES = "AES";

	/**
	 * 加密、解密算法AES / 工作模式ECB / 填充方式 PKCS5Padding
	 * Java 6支持PKCS5Padding填充方式 
	 * Bouncy Castle支持PKCS7Padding填充方式
	 */
	public static final String CIPHER_AES = "AES/ECB/PKCS5Padding";
	
	/**
	 * 密钥算法
	 */
	public static final String KEY_IDEA = "IDEA";

	/**
	 * 加密、解密算法 IDEA/ 工作模式 ECB/ 填充方式PKCS5Padding
	 */
	public static final String CIPHER_IDEA = "IDEA/ECB/PKCS5Padding";
	
	
	
	
	/**DES加密解密过程*/
	@Test public void DES_EncryprDecrypt() throws NoSuchAlgorithmException,NoSuchProviderException, 
		InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		//1、初始化获得秘钥
		//实例化密钥生成器，用56位的秘钥
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_DES);
		//	//用64bit密钥
		//	KeyGenerator keyGenerator2 = KeyGenerator.getInstance(CIPHER_ALGORITHM, "BC");

		//初始化密钥生成器 将下述代码kg.init(56); 替换为kg.init(64);
		keyGenerator.init(56, new SecureRandom());
		//	//用64bit密钥
		//	keyGenerator.init(64,new SecureRandom());

		// 生成秘密密钥
		SecretKey secretKey1 = keyGenerator.generateKey();//每次生成都不一样
		byte[] key = secretKey1.getEncoded();
		System.out.println("DES生成秘钥：\t"+Base64.encodeBase64String(key));

		//2、加密过程
		//2.1 还原秘钥【可省去，直接到2.2步骤】
		// 实例化DES密钥材料
		DESKeySpec dks = new DESKeySpec(key);
		// 实例化秘密密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_DES);
		// 生成秘密密钥
		SecretKey secretKey2 = keyFactory.generateSecret(dks);
		
		//2.2 加密
		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_DES);
		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, secretKey2);//此处key用secretKey1也是一样的
		// 执行操作
		byte[] encryptOutput = cipher.doFinal(input);
		System.out.println("'"+str+"'\tDES加密后BASE64：\t"+Base64.encodeBase64String(encryptOutput));

		//3、解密
		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, secretKey2);
		// 执行操作
		byte[] dencryptOutput = cipher.doFinal(encryptOutput);
		System.out.println("'"+str+"'\tDES解密后BASE64：\t"+Base64.encodeBase64String(dencryptOutput));
		System.out.println("'"+str+"'\tDES解密后：\t"+new String(dencryptOutput));

	}
	
	/**DES知道秘钥后的解密测试*/
	@Test public void DES_Decrypt() throws InvalidKeyException, NoSuchAlgorithmException,
			InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException{
		// 实例化DES密钥材料
		DESKeySpec dks = new DESKeySpec( Base64.decodeBase64("hnZntuAgH4w="));
		// 实例化秘密密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_DES);
		// 生成秘密密钥
		SecretKey secretKey = keyFactory.generateSecret(dks);
		
		//3、解密
		Cipher cipher = Cipher.getInstance(CIPHER_DES);
		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		// 执行操作
		byte[] dencryptOutput = cipher.doFinal(Base64.decodeBase64("iDhf/FCgKq16gWAAnK1u3Q=="));
		System.out.println("'"+str+"'\tDES解密后BASE64：\t"+Base64.encodeBase64String(dencryptOutput));
		System.out.println("'"+str+"'\tDES解密后：\t"+new String(dencryptOutput));
	}
	

	/**DESede的加密解密过程*/
	@Test public void DESede_EncryptDecrypt() throws NoSuchAlgorithmException,NoSuchProviderException, 
	InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		//1、初始化获得秘钥
		//实例化密钥生成器
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_DESede);
		//初始化密钥生成器 DESede 要求密钥长度为 112位或168位
		keyGenerator.init(168);
		// 生成秘密密钥
		SecretKey secretKey1 = keyGenerator.generateKey();
		byte[] key = secretKey1.getEncoded();
		System.out.println("DESede生成秘钥：\t"+Base64.encodeBase64String(key));
		
		//2、加密过程
		//2.1 还原秘钥【可省去，直接到2.2步骤】
		// 实例化DES密钥材料
		DESedeKeySpec dks = new DESedeKeySpec(key);
		// 实例化秘密密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_DESede);
		// 生成秘密密钥
		SecretKey secretKey2 = keyFactory.generateSecret(dks);//每次生成都不一样
		
		//2.2 加密
		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_DESede);
		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, secretKey2);//此处key用secretKey1也是一样的
		// 执行操作
		byte[] encryptOutput = cipher.doFinal(input);
		System.out.println("'"+str+"'\tDESede加密后BASE64：\t"+Base64.encodeBase64String(encryptOutput));
		
		//3、解密
		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, secretKey2);
		// 执行操作
		byte[] dencryptOutput = cipher.doFinal(encryptOutput);
		System.out.println("'"+str+"'\tDESede解密后BASE64：\t"+Base64.encodeBase64String(dencryptOutput));
		System.out.println("'"+str+"'\tDESede解密后：\t"+new String(dencryptOutput));
	}
	
	/**DESede知道秘钥时的解密过程*/
	@Test public void DESede_Decrypt() throws InvalidKeyException, NoSuchAlgorithmException, 
			InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		//1 还原秘钥
		// 实例化DES密钥材料
		DESedeKeySpec dks = new DESedeKeySpec(Base64.decodeBase64("8uk35gh2SSBMAWtAE21DzZ3xH8TaCKsV"));
		// 实例化秘密密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_DESede);
		// 生成秘密密钥
		SecretKey secretKey = keyFactory.generateSecret(dks);//每次生成都不一样
		
		//2、解密
		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_DESede);
		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		// 执行操作
		byte[] dencryptOutput = cipher.doFinal(Base64.decodeBase64("U4KpFJyIdILArAFQhFuSPQ=="));
		System.out.println("'"+str+"'\tDESede解密后BASE64：\t"+Base64.encodeBase64String(dencryptOutput));
		System.out.println("'"+str+"'\tDESede解密后：\t"+new String(dencryptOutput));
	}
	
	/**AES的加密解密过程*/
	@Test public void AES_EncryptDecrypt() throws NoSuchAlgorithmException,NoSuchProviderException, 
	InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		//1、初始化获得秘钥
		//实例化密钥生成器
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_AES);
		// AES 要求密钥长度为 128位、192位或 256位
		keyGenerator.init(128);
		// 生成秘密密钥
		SecretKey secretKey1 = keyGenerator.generateKey();
		byte[] key = secretKey1.getEncoded();
		System.out.println("AES生成秘钥：\t"+Base64.encodeBase64String(key));
		
		//2、加密过程
		//2.1 还原秘钥【可省去，直接到2.2步骤】
		// 实例化DES密钥材料 【和DES、DESege相当不一样的方式】
		SecretKey secretKey2 = new SecretKeySpec(key, KEY_AES);;//每次生成都不一样
		
		//2.2 加密
		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_AES);
		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, secretKey2);//此处key用secretKey1也是一样的
		// 执行操作
		byte[] encryptOutput = cipher.doFinal(input);
		System.out.println("'"+str+"'\tAES加密后BASE64：\t"+Base64.encodeBase64String(encryptOutput));
		
		//3、解密
		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, secretKey2);
		// 执行操作
		byte[] dencryptOutput = cipher.doFinal(encryptOutput);
		System.out.println("'"+str+"'\tAES解密后BASE64：\t"+Base64.encodeBase64String(dencryptOutput));
		System.out.println("'"+str+"'\tAES解密后：\t"+new String(dencryptOutput));
	}
	
	/**AES知道秘钥时的解密过程*/
	@Test public void AES_Decrypt() throws InvalidKeyException, NoSuchAlgorithmException, 
	InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		//1 还原秘钥
		// 生成秘密密钥
		SecretKey secretKey = new SecretKeySpec(Base64.decodeBase64("gF8hLYFa0EhPFzoN2tlqeQ=="), KEY_AES);//每次生成都不一样
		
		//2、解密
		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_AES);
		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		// 执行操作
		byte[] dencryptOutput = cipher.doFinal(Base64.decodeBase64("deBJO+hala1qLBSN1V/rXA=="));
		System.out.println("'"+str+"'\tDESede解密后BASE64：\t"+Base64.encodeBase64String(dencryptOutput));
		System.out.println("'"+str+"'\tDESede解密后：\t"+new String(dencryptOutput));
	}
	
	/**IDEA的加密解密过程【貌似不行，秘钥无法生成】*/
	@Test public void IDEA_EncryptDecrypt() throws NoSuchAlgorithmException,NoSuchProviderException, 
	InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		//1、初始化获得秘钥
		// 加入BouncyCastleProvider支持
		Security.addProvider(new BouncyCastleProvider());
		//实例化密钥生成器
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_IDEA);
		// 初始化
		keyGenerator.init(128);
		// 生成秘密密钥
		SecretKey secretKey1 = keyGenerator.generateKey();
		byte[] key = secretKey1.getEncoded();
		System.out.println("IDEA生成秘钥：\t"+Base64.encodeBase64String(key));
		
		//2、加密过程
		//2.1 还原秘钥【可省去，直接到2.2步骤】
		// 实例化IDEA密钥材料 【类似AES但和DES、DESege相当不一样的方式】
		SecretKey secretKey2 = new SecretKeySpec(key, KEY_IDEA);//每次生成都不一样
		
		//2.2 加密
		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_IDEA);
		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, secretKey2);//此处key用secretKey1也是一样的
		// 执行操作
		byte[] encryptOutput = cipher.doFinal(input);
		System.out.println("'"+str+"'\tIDEA加密后BASE64：\t"+Base64.encodeBase64String(encryptOutput));
		
		//3、解密
		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, secretKey2);
		// 执行操作
		byte[] dencryptOutput = cipher.doFinal(encryptOutput);
		System.out.println("'"+str+"'\tIDEA解密后BASE64：\t"+Base64.encodeBase64String(dencryptOutput));
		System.out.println("'"+str+"'\tIDEA解密后：\t"+new String(dencryptOutput));
	}
	
	/**IDEA知道秘钥时的解密过程*/
	@Test public void IDEA_Decrypt() throws InvalidKeyException, NoSuchAlgorithmException, 
	InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		//1 还原秘钥
		// 生成秘密密钥
		SecretKey secretKey = new SecretKeySpec(Base64.decodeBase64("gF8hLYFa0EhPFzoN2tlqeQ=="), KEY_AES);//每次生成都不一样
		
		//2、解密
		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_AES);
		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		// 执行操作
		byte[] dencryptOutput = cipher.doFinal(Base64.decodeBase64("deBJO+hala1qLBSN1V/rXA=="));
		System.out.println("'"+str+"'\tDESede解密后BASE64：\t"+Base64.encodeBase64String(dencryptOutput));
		System.out.println("'"+str+"'\tDESede解密后：\t"+new String(dencryptOutput));
	}
}
