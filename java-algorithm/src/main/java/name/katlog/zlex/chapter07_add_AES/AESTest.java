package name.katlog.zlex.chapter07_add_AES;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;


/**
 * @moudle: AESTest 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年8月7日 下午5:07:46
 *
 */
public class AESTest {
	
	private static final String str = "MD消息摘要";
	private static final byte[] data = str.getBytes();
	
	@Test public void test(){
		
		KeyGenerator keyGen = null;
		try {
			keyGen = KeyGenerator.getInstance("AES");// 密钥生成器
			keyGen.init(128); // 默认128，获得无政策权限后可为192或256
			SecretKey secretKey = keyGen.generateKey();// 生成密钥
			byte[] key = secretKey.getEncoded();// 密钥字节数组
			
			SecretKey secretKey1 = new SecretKeySpec(key, "AES");// 恢复密钥
			Cipher cipher = Cipher.getInstance("AES");// Cipher完成加密或解密工作类
			cipher.init(Cipher.ENCRYPT_MODE, secretKey1);// 对Cipher初始化，解密模式
			byte[] encryptByte = cipher.doFinal(data);// 加密data
			
			SecretKey secretKey2 = new SecretKeySpec(key, "AES");// 恢复密钥
			cipher.init(Cipher.DECRYPT_MODE, secretKey2);// 对Cipher初始化，解密模式
			byte[] cipherByte = cipher.doFinal(data);// 解密data
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}


	}
}
