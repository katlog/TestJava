package encryptDecrypt;
/**
 * 
 */

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
import java.security.Signature;
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
 * @author jiong.peng
 * 
 */
public class FangdaiSDK {

	//信用租房 相寓私钥
//	private static final String xiangyustockPriKey = PropertiesFileUtil.getProperties(StaticVariable.JR_PROPERTY_FILE_PATH).get("XIANGYU_PRIVATE_KEY");
//	//信用租房 相寓公钥
//	private static final String xiangyustockPubKey = PropertiesFileUtil.getProperties(StaticVariable.JR_PROPERTY_FILE_PATH).get("XIANGYU_PUBLIC_KEY");
//	//信用租房 房司令公钥
//	private static final String fslstockPubKey = PropertiesFileUtil.getProperties(StaticVariable.JR_PROPERTY_FILE_PATH).get("FSL_PUBLIC_KEY");
//	//APP 公钥
//	private static final String appstockPubKey = PropertiesFileUtil.getProperties(StaticVariable.JR_PROPERTY_FILE_PATH).get("APP_PUBLIC_KEY_VALUE");
	
	private static final String transformation = "RSA/ECB/PKCS1Padding";

	/**
	 * RSA最大加密明文大小(117)
	 */
	public static final int MAX_ENCRYPT_BLOCK = 117;
	/**
	 * RSA最大解密密文大小(128)
	 */
	public static final int MAX_DECRYPT_BLOCK = 128;

	/** */
	/**
	 * 获取公钥的key
	 */
	public static final String PUBLIC_KEY = "RSAPublicKey";

	/** */
	/**
	 * 获取私钥的key
	 */
	public static final String PRIVATE_KEY = "RSAPrivateKey";

	/**
	 * 签名算法
	 */
	public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

	/**
	 * 缓存密钥
	 */
	private static final Map<String, Object> keyMap = new HashMap<String, Object>();

	


	/**
	 * 公钥加密
	 */
	public static String encrypt(String pubKeyStr, String data)
			throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] encodedKey = Base64.decodeBase64(pubKeyStr);
	    PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
		return encrypt(pubKey, data);
	}
	
	/**
     * 私钥加密
     */
    public static String encryptPri(String priKeyStr, String data)
            throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] encodedKey = Base64.decodeBase64(priKeyStr);
        PrivateKey priKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
        return encrypt(priKey, data);
    }

	/**
	 * 加密
	 */
	public static String encrypt(Key key, String dataStr)
			throws RuntimeException {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 私钥解密
	 */
	public static String decrypt(String prikeyStr, String data)
			throws Exception {
		PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decodeBase64(prikeyStr) ); 
    	KeyFactory keyf 				= KeyFactory.getInstance("RSA");
    	PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);
		return decrypt(priKey, data);
	}
	
	/**
     * 公钥解密
     */
    public static String decryptPub(String pubkeyStr, String data)
            throws Exception {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec( Base64.decodeBase64(pubkeyStr) ); 
        KeyFactory keyf                 = KeyFactory.getInstance("RSA");
        PublicKey publicKey             = keyf.generatePublic(x509KeySpec);
        return decrypt(publicKey, data);
    }
    
	

	/**
	 * 解密
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
