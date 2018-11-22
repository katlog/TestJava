package encryptDecrypt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class TestRSASimple {
    private static final String transformation = "RSA/ECB/PKCS1Padding";
    /**
     * 
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
    
    private static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKdd4Df1KpSaAwN/" + 
        "EpFDZJihGiIWbla66gh6rL8+9CQJBpz6j2S4G/J6JgBYygXJjoY+GsMclQMJDaft" + 
        "OPYCvrQAXuIkPkfWMcvRQTS13FA2AfpFp9WsO7bqI2fl8+LE2bC50hJ1XWVonNYv" + 
        "4hy1yprNvlgOZc7q2zz4Utcti3apAgMBAAECgYBgq6p859EmcCbZeCZjXUfXmQYd" + 
        "NlOZKPFQ4HqzJEukHMqDFX6JsIYD8mXMwXw/B4KZK3oNxI6VaD9JBY5nGoLHSi7/" + 
        "a/QO9QKba7YIWgB2jhvay2ke5fjfGqGKGl1ZXKks0vPM/3usnrB/wVS6V7rGhQmb" + 
        "18rHXofZQwBPq2WF+QJBANguAplq9sHFTCuIoWqwg0jrkBmWtDB/kWrMMfBB28tS" + 
        "NSMbutWklk06LPT7boHfxUdEv3TjdgHSkE9RdWXfNNMCQQDGMhRk47eY1FGQNJP/" + 
        "KjpN39Umx2n1vOWJuVkSr0GfM4hUOr7GftLnvKTqovkoq4FbPwKy628K/RIjLAYX" + 
        "cGkTAkBYO+dJsRPIIPwm5JxljGf5UlQ9bXpcNSUoPvG/C1JnmUUg8EeAva2vFaiV" + 
        "0S3YinndzdyLG+/lbIkEJs5Q5xqLAkAlHBVjepvS2tZWyPF5EoAOOr3fX/FAGNll" + 
        "uzWpYPVJiM2a79Duu6pjx5edc1xxuAMlgZvQwnJtPUtbGJAfbWXPAkAvyDh8UVEh" + 
        "qCHvSK7f+6rhlsVagT+yNjV0Odt7fh9MpF1odmD1DPAjlPS3wJPU9mISQq05/5JM" + 
        "9WPTyLuoEYKI";
    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnXeA39SqUmgMDfxKRQ2SYoRoi" + 
        "Fm5WuuoIeqy/PvQkCQac+o9kuBvyeiYAWMoFyY6GPhrDHJUDCQ2n7Tj2Ar60AF7i" + 
        "JD5H1jHL0UE0tdxQNgH6RafVrDu26iNn5fPixNmwudISdV1laJzWL+Ictcqazb5Y" + 
        "DmXO6ts8+FLXLYt2qQIDAQAB";
    
    
    
    
    
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
     * 
     * @param key
     *            密钥
     * @param data
     *            源数据bytes
     * @return
     * @throws RuntimeException
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
     * 解密
     * 
     * @param key
     *            密钥
     * @param data
     *            需要解密的bytes
     * @return
     * @throws RuntimeException
     */
    public static String decrypt(String prikeyStr, String data)
            throws Exception {
        PKCS8EncodedKeySpec priPKCS8    = new PKCS8EncodedKeySpec( Base64.decodeBase64(prikeyStr) ); 
        KeyFactory keyf                 = KeyFactory.getInstance("RSA");
        PrivateKey priKey               = keyf.generatePrivate(priPKCS8);
        return decrypt(priKey, data);
    }

    /**
     * 解密
     * 
     * @param key
     *            密钥
     * @param data
     *            需要解密的bytes
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
    
    
    @Test
    public void test(){
        String str = "我是一个好人！";
        try {
            String encrypt = encrypt(PUBLIC_KEY, str);
            System.out.println(encrypt);
            String decrypt = decrypt(PRIVATE_KEY, encrypt);
            System.out.println(decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
