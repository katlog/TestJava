package name.katlog.zlex.chapter06_1_1MD;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;

/**
 * @moudle: TestMDSalt 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年12月18日 下午2:11:58
 *
 */
public class TestMDSalt {
	
    /**  
     * 普通MD5  
     * @author daniel  
     * @time 2016-6-11 下午8:00:28  
     * @param inStr  
     * @return  
     */  
    public static String MD5(String input) {  
        MessageDigest md5 = null;  
        try {  
            md5 = MessageDigest.getInstance("MD5");  
        } catch (NoSuchAlgorithmException e) {  
            return "check jdk";  
        } catch (Exception e) {  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = input.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++) {  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    }  
   
       
       
       
    /**  
     * 加盐MD5  
     * @author daniel  
     * @time 2016-6-11 下午8:45:04  
     * @param password  
     * @return  
     */  
        public static String generate(String password) {  
            Random r = new Random();  
            StringBuilder sb = new StringBuilder(16);  
            sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));  
            int len = sb.length();  
            if (len < 16) {  
                for (int i = 0; i < 16 - len; i++) {  
                    sb.append("0");  
                }  
            }  
            String salt = sb.toString();  
            System.out.println("盐："+salt);
            password = md5Hex(password + salt);  // 生成32位
            
            // 生成加盐字符串：将salt插入到md5hex中方便验证
            char[] cs = new char[48];  
            for (int i = 0; i < 48; i += 3) {  
                cs[i] = password.charAt(i / 3 * 2);  
                char c = salt.charAt(i / 3);  
                cs[i + 1] = c;  
                cs[i + 2] = password.charAt(i / 3 * 2 + 1);  
            }  
            return new String(cs);  
        }  
  
        /**  
         * 校验加盐后是否和原文一致  
         * @author daniel  
         * @time 2016-6-11 下午8:45:39  
         * @param password  
         * @param md5  
         * @return  
         */  
        public static boolean verify(String password, String md5) {  
            char[] cs1 = new char[32];  
            char[] cs2 = new char[16];  
            for (int i = 0; i < 48; i += 3) {  
                cs1[i / 3 * 2] = md5.charAt(i);  
                cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);  
                cs2[i / 3] = md5.charAt(i + 1);  
            }  
            String salt = new String(cs2);  
            return md5Hex(password + salt).equals(new String(cs1));  
        }  
  
        /**  
         * 获取十六进制字符串形式的MD5摘要  
         */  
        private static String md5Hex(String src) {  
            try {  
                MessageDigest md5 = MessageDigest.getInstance("MD5");  
                byte[] bs = md5.digest(src.getBytes());  
                return new String(new Hex().encode(bs));  
            } catch (Exception e) {  
                return null;  
            }  
        }  
        
	// 测试主函数
	public static void main(String args[]) {
		// 原文
		String plaintext = "DingSai";
		// plaintext = "123456";
		System.out.println("原始：" + plaintext);
		System.out.println("普通MD5后：" + MD5(plaintext));
		// 获取加盐后的MD5值
		String ciphertext = generate(plaintext);
		System.out.println("加盐后MD5：" + ciphertext);
		System.out.println("是否是同一字符串:" + verify(plaintext, ciphertext));
		/**
		 * 其中某次DingSai字符串的MD5值
		 */
		String[] tempSalt = { "c4d980d6905a646d27c0c437b1f046d4207aa2396df6af86",
				"66db82d9da2e35c95416471a147d12e46925d38e1185c043", "61a718e4c15d914504a41d95230087a51816632183732b5a" };
		for (String temp : tempSalt) {
			System.out.println("是否是同一字符串:" + verify(plaintext, temp));
		}
	}
}
