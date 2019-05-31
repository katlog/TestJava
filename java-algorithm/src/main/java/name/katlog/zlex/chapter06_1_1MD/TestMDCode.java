/**
 * 2009-9-3
 */
package name.katlog.zlex.chapter06_1_1MD;

import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

/**
 * MD校验
 */
public class TestMDCode {

	private static final String str = "MD消息摘要";
	private static final byte[] input = str.getBytes();
	private static MessageDigest md2 ;
	private static MessageDigest md4;
	private static MessageDigest md5 ;
	
	private static MessageDigest RipeMD128 ;
	private static MessageDigest RipeMD160;
	private static MessageDigest RipeMD256 ;
	
	private static MessageDigest Tiger ;
	private static MessageDigest Whirlpool;
	private static MessageDigest GOST3411 ;
	
	static{
		try {
			md2 = MessageDigest.getInstance("MD2");
			md5 = MessageDigest.getInstance("MD5");
			
			// 加入BouncyCastleProvider支持,才能获取md4、RipeMD128、RipeMD160、RipeMD256
			Security.addProvider(new BouncyCastleProvider());
			md4 = MessageDigest.getInstance("MD4");
			
			RipeMD128 = MessageDigest.getInstance("RipeMD128");
			RipeMD160 = MessageDigest.getInstance("RipeMD160");
			RipeMD256 = MessageDigest.getInstance("RipeMD256");
			
			//加入BouncyCastleProvider支持, Tiger Whirlpool GOST3411
			Tiger = MessageDigest.getInstance("Tiger");
			Whirlpool = MessageDigest.getInstance("Whirlpool");
			GOST3411 = MessageDigest.getInstance("GOST3411");
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	@Test public void md2(){
		System.out.println("md加密方式----------------------------");
		System.out.println("'"+str+"'\t加密前byte\t"+Arrays.toString(input));
		//md加密
		byte[] digestMd = md2.digest(input);
		System.out.println("'"+str+"'\t加密后byte\t"+Arrays.toString(digestMd));
	}
	
	@Test public void md4(){
		System.out.println("md4加密方式----------------------------");
		System.out.println("'"+str+"'\t加密前byte\t"+Arrays.toString(input));
		// 执行消息摘要
		byte[] digestMd = md4.digest(input);
		System.out.println("'"+str+"'\t加密后byte\t"+Arrays.toString(digestMd));
		// 做十六进制编码处理
		System.out.println("'"+str+"'\t加密后十六进制\t"+new String(Hex.encode(digestMd)));
		System.out.println("'"+str+"'\t加密后十六进制byte\t"+Arrays.toString(Hex.encode(digestMd)));
	}

	@Test public void md5(){
		System.out.println("md5加密方式----------------------------");
		System.out.println("'"+str+"'\t加密前byte\t"+Arrays.toString(input));
		//使用java.sercurity.MessageDigest
		byte[] digestMd5 = md5.digest(input);
		System.out.println("'"+str+"'\tMessageDigest加密后byte\t"+Arrays.toString(digestMd5));
		//使用DigestUtils.md5
		digestMd5 = DigestUtils.md5(input);
		System.out.println("'"+str+"'\tDigestUtils.md5加密后byte\t"+Arrays.toString(digestMd5));
		//使用DigestUtils.md5
		String digestMd = DigestUtils.md5Hex(input);
		System.out.println("'"+str+"'\tDigestUtils.md5加密十六进制后byte\t"+digestMd);
	}
	
	@Test public void RipeMD128(){
		System.out.println("RipeMD128加密方式----------------------------");
		System.out.println("'"+str+"'\t加密前byte\t"+Arrays.toString(input));
		//md加密
		byte[] digestMd = RipeMD128.digest(input);
		System.out.println("'"+str+"'\t加密后byte\t"+Arrays.toString(digestMd));
	}
	
	@Test public void RipeMD160(){
		System.out.println("RipeMD160加密方式----------------------------");
		System.out.println("'"+str+"'\t加密前byte\t"+Arrays.toString(input));
		//md加密
		byte[] digestMd = RipeMD160.digest(input);
		System.out.println("'"+str+"'\t加密后byte\t"+Arrays.toString(digestMd));
	}
	
	@Test public void RipeMD256(){
		System.out.println("RipeMD256加密方式----------------------------");
		System.out.println("'"+str+"'\t加密前byte\t"+Arrays.toString(input));
		//md加密
		byte[] digestMd = RipeMD256.digest(input);
		System.out.println("'"+str+"'\t加密后byte\t"+Arrays.toString(digestMd));
	}
	
	@Test public void Tiger(){
		System.out.println("Tiger加密方式----------------------------");
		System.out.println("'"+str+"'\t加密前byte\t"+Arrays.toString(input));
		//md加密
		byte[] digestMd = Tiger.digest(input);
		System.out.println("'"+str+"'\t加密后byte\t"+Arrays.toString(digestMd));
	}
	
	@Test public void Whirlpool(){
		System.out.println("Whirlpool加密方式----------------------------");
		System.out.println("'"+str+"'\t加密前byte\t"+Arrays.toString(input));
		//md加密
		byte[] digestMd = Whirlpool.digest(input);
		System.out.println("'"+str+"'\t加密后byte\t"+Arrays.toString(digestMd));
	}
	
	@Test public void GOST3411(){
		System.out.println("GOST3411加密方式----------------------------");
		System.out.println("'"+str+"'\t加密前byte\t"+Arrays.toString(input));
		//md加密
		byte[] digestMd = GOST3411.digest(input);
		System.out.println("'"+str+"'\t加密后byte\t"+Arrays.toString(digestMd));
	}
	
	/**
	 * 验证文件的MD5值
	 */
	@Test public void DigestInputStream() throws Exception {
		// 构建文件输入流
		FileInputStream fis = new FileInputStream(this.getClass().getResource("md.file").getFile());
		// 初始化MessageDigest，并指定MD5算法
		DigestInputStream dis = new DigestInputStream(fis, md5);
		// 流缓冲大小
		int buf = 1024;
		// 缓冲字节数组
		byte[] buffer = new byte[buf];
		// 当读到值大于-1就继续读
		int read = dis.read(buffer, 0, buf);
		while (read > -1) {
			read = dis.read(buffer, 0, buf);
		}
		// 关闭流
		dis.close();
		// 获得MessageDigest
		MessageDigest md = dis.getMessageDigest();
		// 摘要处理
		byte[] b = md.digest();
		// 十六进制转换
		String md5hex = org.apache.commons.codec.binary.Hex.encodeHexString(b);
		System.out.println("md.file文件的md5:\t"+md5hex);
	}

	/**
	 * 验证文件的MD5值
	 */
	@Test public void ByDigestUtils() throws Exception {
		// 构建文件输入流
		FileInputStream fis = new FileInputStream(this.getClass().getResource("md.file").getFile());
		// 使用DigestUtils做MD5Hex处理
		String md5hex = DigestUtils.md5Hex(fis);
		// 关闭流
		fis.close();
		System.out.println("md.filemd.file文件的md5:\t"+md5hex);
	}
}
