
/**  
 * @Title: TestRSA1.java
 * @Package: org.person.dfw.TestRSA1
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月20日 下午2:31:57
 * @version: V1.0  
 */ 
package encryptDecrypt;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

import org.apache.commons.codec.binary.Base64;

/**
 * @moudle: TestRSA1
 * @version:v1.0
 * @date: 2017年3月20日 下午2:31:57
 *
 */
public class TestRSA {
	
	private static final String transformation = "RSA/ECB/PKCS1Padding";
	
	/**公钥的key*/
	public static final String PUBLIC_KEY = "RSAPublicKey";
	/**私钥的key*/
	public static final String PRIVATE_KEY = "RSAPrivateKey";
	/**缓存密钥*/
	private static final Map<String, Object> keyMap = new HashMap<String, Object>();
	
	/**RSA最大加密明文大小(117)*/
	public static final int MAX_ENCRYPT_BLOCK = 117;
	/**RSA最大解密密文大小(128)*/
	public static final int MAX_DECRYPT_BLOCK = 128;
	/**
	 * 读取X509证书
	 * 
	 * @param file
	 * @return
	 * @throws SecurityException
	 */
	private static X509Certificate certDispose(String file)
			throws SecurityException {
		InputStream input = null;
		X509Certificate x509certificate;
		try {
			input = new BufferedInputStream(new FileInputStream(file));
			CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
			x509certificate = (X509Certificate) certificatefactory.generateCertificate(input);

			if (null != input)
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		} catch (Exception e) {
			throw new SecurityException("读取证书失败", e);
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return x509certificate;
	}
	
	/**
	 * 加载指定路径证书文件，获取公钥
	 * 
	 * @param keyPath
	 *            证书文件路径
	 * @return 公钥对象
	 * @throws RuntimeException
	 */
	public static PublicKey loadPublicKey(String keyPath)
			throws RuntimeException {
		X509Certificate cert = certDispose(keyPath);
		PublicKey key = cert.getPublicKey();
		keyMap.put(PUBLIC_KEY, key);
		return key;
	}
	
	
	/**
	 * 加密
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            源数据bytes
	 * @return
	 * @throws RuntimeException
	 */
	public static String encrypt(String pubKeyStr, String data)
			throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] encodedKey = Base64.decodeBase64(pubKeyStr);
	    PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
		return encrypt(pubKey, data);
	}
	
	
	/**
	 * 加密
	 * @param key  密钥
	 * @param data 源数据bytes
	 * @return
	 * @throws RuntimeException
	 */
	public static String encrypt(Key key, String dataStr) throws RuntimeException {
		try {
			byte[] data = dataStr.getBytes("UTF-8");
			Cipher cipher = Cipher.getInstance(transformation);
			cipher.init(Cipher.ENCRYPT_MODE, key);

			int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
			int leavedSize = data.length % MAX_ENCRYPT_BLOCK;
			int blocksSize = leavedSize != 0 ? data.length / MAX_ENCRYPT_BLOCK
					+ 1 : data.length / MAX_ENCRYPT_BLOCK;
			byte[] raw = new byte[outputSize * blocksSize];
			int i = 0;
			while (data.length - i * MAX_ENCRYPT_BLOCK > 0) {
				if (data.length - i * MAX_ENCRYPT_BLOCK > MAX_ENCRYPT_BLOCK)
					cipher.doFinal(data, i * MAX_ENCRYPT_BLOCK,
							MAX_ENCRYPT_BLOCK, raw, i * outputSize);
				else
					cipher.doFinal(data, i * MAX_ENCRYPT_BLOCK, data.length - i
							* MAX_ENCRYPT_BLOCK, raw, i * outputSize);
				i++;
			}
			return Base64.encodeBase64String(raw);

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException(e);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		} catch (ShortBufferException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解密
	 * @param key 密钥
	 * @param data 需要解密的bytes
	 * @return
	 * @throws RuntimeException
	 */
	public static String decrypt(String prikeyStr, String data)
			throws Exception {
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec( Base64.decodeBase64(prikeyStr) ); 
    	KeyFactory keyf = KeyFactory.getInstance("RSA");
    	PrivateKey priKey = keyf.generatePrivate(priPKCS8);
		return decrypt(priKey, data);
	}

	/**
	 * 解密
	 * @param key 密钥
	 * @param data 需要解密的bytes
	 * @return
	 * @throws RuntimeException
	 */
	public static String decrypt(Key key, String dataStr)
			throws RuntimeException {
		try {
			byte[] data = Base64.decodeBase64(dataStr);
			Cipher cipher = Cipher.getInstance(transformation);
			cipher.init(Cipher.DECRYPT_MODE, key);
			ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
			int j = 0;

			while (data.length - j * MAX_DECRYPT_BLOCK > 0) {
				bout.write(cipher.doFinal(data, j * MAX_DECRYPT_BLOCK,
						MAX_DECRYPT_BLOCK));
				j++;
			}
			return new String(bout.toByteArray(), "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
