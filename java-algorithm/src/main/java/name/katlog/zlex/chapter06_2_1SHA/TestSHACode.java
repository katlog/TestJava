package name.katlog.zlex.chapter06_2_1SHA;

import static org.junit.Assert.assertEquals;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

/**
 * SHA校验
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class TestSHACode {

	private static final String str = "SHA消息摘要";
	private static final byte[] input = str.getBytes();
	private static MessageDigest SHA ;
	private static MessageDigest SHA_256;
	private static MessageDigest SHA_384;
	private static MessageDigest SHA_512 ;
	private static MessageDigest SHA_224 ;
	
	static{
		try {
			SHA = MessageDigest.getInstance("SHA");
			SHA_256 = MessageDigest.getInstance("SHA-256");
			SHA_384 = MessageDigest.getInstance("SHA-384");
			SHA_512 = MessageDigest.getInstance("SHA-512");
			// 加入BouncyCastleProvider支持
			Security.addProvider(new BouncyCastleProvider());
			// 初始化MessageDigest
			SHA_224 = MessageDigest.getInstance("SHA-224");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	@Test public void SHA(){
		System.out.println("SHA加密方式----------------------------");
		System.out.println("'"+str+"'\t加密前byte\t"+Arrays.toString(input));
		//SHA加密
		byte[] digestSHA = SHA.digest(input);
		System.out.println("'"+str+"'\tMessageDigest加密后byte\t"+Arrays.toString(digestSHA));
		digestSHA = DigestUtils.sha(input);
		System.out.println("'"+str+"'\tDigestUtils.sha加密后byte\t"+Arrays.toString(digestSHA));
		String digestStr = DigestUtils.shaHex(input);
		System.out.println("'"+str+"'\tDigestUtils.sha加密后十六进制\t"+digestStr);
	}
	
	@Test public void SHA_256(){
		System.out.println("SHA-256加密方式----------------------------");
		System.out.println("'"+str+"'\t加密前byte\t"+Arrays.toString(input));
		//SHA-256加密
		byte[] digestSHA = SHA_256.digest(input);
		System.out.println("'"+str+"'\tMessageDigest加密后byte\t"+Arrays.toString(digestSHA));
		digestSHA = DigestUtils.sha256(input);
		System.out.println("'"+str+"'\tDigestUtils.sha256加密后byte\t"+Arrays.toString(digestSHA));
		String digestStr = DigestUtils.sha256Hex(input);
		System.out.println("'"+str+"'\tDigestUtils.sha256加密后十六进制\t"+digestStr);
	}
	
	@Test public void SHA_384(){
		System.out.println("SHA-384加密方式----------------------------");
		System.out.println("'"+str+"'\t加密前byte\t"+Arrays.toString(input));
		//SHA-384加密
		byte[] digestSHA = SHA_384.digest(input);
		System.out.println("'"+str+"'\tMessageDigest加密后byte\t"+Arrays.toString(digestSHA));
		digestSHA = DigestUtils.sha384(input);
		System.out.println("'"+str+"'\tDigestUtils.sha384加密后byte\t"+Arrays.toString(digestSHA));
		String digestStr = DigestUtils.sha384Hex(input);
		System.out.println("'"+str+"'\tDigestUtils.sha384加密后十六进制\t"+digestStr);
	}
	
	@Test public void SHA_512(){
		System.out.println("SHA-512加密方式----------------------------");
		System.out.println("'"+str+"'\t加密前byte\t"+Arrays.toString(input));
		//SHA-512加密
		byte[] digestSHA = SHA_512.digest(input);
		System.out.println("'"+str+"'\tMessageDigest加密后byte\t"+Arrays.toString(digestSHA));
		digestSHA = DigestUtils.sha512(input);
		System.out.println("'"+str+"'\tDigestUtils.sha512加密后byte\t"+Arrays.toString(digestSHA));
		String digestStr = DigestUtils.sha512Hex(input);
		System.out.println("'"+str+"'\tDigestUtils.sha512加密后十六进制\t"+digestStr);
	}
	
	@Test public void SHA_224(){
		System.out.println("SHA-224加密方式----------------------------");
		System.out.println("'"+str+"'\t加密前byte\t"+Arrays.toString(input));
		//SHA-224加密
		byte[] digestSHA = SHA_224.digest(input);
		System.out.println("'"+str+"'\tMessageDigest加密后byte\t"+Arrays.toString(digestSHA));
		System.out.println("'"+str+"'\t加密后十六进制\t"+new String(Hex.encode(digestSHA)));
	}
	
	/**
	 * 验证文件的SHA值
	 */
	@Test public void testByMessageDigest() throws Exception {
		// 构建文件输入流
		FileInputStream fis = new FileInputStream(this.getClass().getResource("sha.file").getFile());
		// 使用DataInputStream包装文件输入流
		DataInputStream dis = new DataInputStream(fis);
		// 获得可用的流字节长度，这里指文件长度
		int len = dis.available();
		// 初始化用于存储文件数据的字节数组
		byte[] data = new byte[len];
		// 一次性将文件信息读取到字节数组中
		dis.readFully(data);
		// 关闭流
		dis.close();
		// 摘要处理
		byte[] b = SHA.digest(data);
		// 十六进制转换
		String md5hex = org.apache.commons.codec.binary.Hex.encodeHexString(b);
		System.out.println("sha.file文件的sha:\t"+md5hex);
	}

	/**
	 * 验证文件的SHA值
	 * 
	 * @throws Exception
	 */
	@Test
	public void testByDigestUtils() throws Exception {
		// 构建文件输入流
		FileInputStream fis = new FileInputStream(this.getClass().getResource("sha.file").getFile());
		// 使用DataInputStream包装文件输入流
		DataInputStream dis = new DataInputStream(fis);
		// 获得可用的流字节长度，这里指文件长度
		int len = dis.available();
		// 初始化用于存储文件数据的字节数组
		byte[] data = new byte[len];
		// 一次性将文件信息读取到字节数组中
		dis.readFully(data);
		// 关闭流
		dis.close();
		// 使用DigestUtils做SHAHex处理
		String shahex = DigestUtils.shaHex(data);
		System.out.println("sha.file文件的sha:\t"+shahex);
	}
}
