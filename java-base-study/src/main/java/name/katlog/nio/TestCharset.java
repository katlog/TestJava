package name.katlog.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Set;
import java.util.SortedMap;

import org.junit.Test;
import name.katlog.util.collections.MapUtil;
import name.katlog.util.Print;


/**
 * @moudle: TestCharset 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年8月4日 下午2:14:52
 *
 */
public class TestCharset {
	
	/**通过字符集来编码、解码*/
	@Test public void _encodeDecode() throws UnsupportedEncodingException{
		
		String str = "爨、。·ˉˇ¨〃々—～‖…‘’“”〔〕〈 〉《》「」『』〖〗【】±+-×÷∧∨∑∏∪∩∈√⊥∥∠⌒⊙∫∮≡≌≈∽∝≠≮≯≤≥∞∶ ∵∴∷♂♀°′\"℃$¤￠￡‰§№☆★〇○";
		
		String[] charsets = new String[]{"iso-8859-1","iso-8859-3","Shift_Jis","x-windows-950","gbk"};
		
		// 方式一
		for (String charsetStr : charsets) {
			Charset charset = Charset.forName(charsetStr);
			
			//编码
			ByteBuffer encode = charset.encode(str);
			byte[] array = encode.array();
			
			//转码
			ByteBuffer byteBuffer = ByteBuffer.wrap(array);
			CharBuffer decode = charset.decode(byteBuffer);
			System.out.println(decode);//本地编码方式模式不能表示所有的Unicode字符，如果某个字符不能表示，它将被转换成？，如iso-8859-1
		}
		//方式二
		for (int i = 0; i < charsets.length; i++) {
			byte[] bytes = str.getBytes(charsets[i]);
			System.out.println(new String(bytes, charsets[i]));
		}
	}
	
	/**获取系统默认字符集*/
	@Test public void defaultCharset(){
		//String的getBytes()采用系统默认字符集
        System.out.println(Charset.defaultCharset().displayName());  
	}
	
	/**获取这个虚拟机可用的所有字符集*/
	@Test public void avanamecharsets(){
		SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
		MapUtil.formatPrint(Print.Style.Vertical, null, availableCharsets);
	}
	
	/**获取全部别名*/
	@Test public void aliases(){
		Charset charset = Charset.forName("iso-8859-1");
		//获取全部别名
		Set<String> aliases = charset.aliases();
		for (String name : aliases) {
			System.out.println(name);
		}
	}
	
	@Test public void _printAllUnicodeChar() throws FileNotFoundException{
		PrintWriter writer = new PrintWriter( new FileOutputStream("basic/org/person/dfw/nio/unicode"));
		
		for(char c=0;c<0xffff;c++){
//			writer.print(c);
			System.out.print(c);
			if(c%100==0) {
//				writer.println();
				System.out.println();
				if ((int)c%10000==0) {
					System.out.println((int)c);
				}
			}
		}
	}
	
	@Test public void test(){
		String s=String.valueOf(Character.toChars(0x2F81A));  
		char[]chars=s.toCharArray();  
		for(char c:chars){  
		    System.out.format("%x",(short)c);  
		}  
		System.out.println(s);
		System.out.println(chars);
	}
	
	public static String getUnicode(String source){
		 String returnUniCode=null;
		 String uniCodeTemp=null;
		 for(int i=0;i<source.length();i++){
		  uniCodeTemp = "\\u"+Integer.toHexString((int)source.charAt(i));
		  returnUniCode=returnUniCode==null?uniCodeTemp:returnUniCode+uniCodeTemp;
		 }
		 System.out.print(source +" 's unicode = "+returnUniCode);
		 return returnUniCode;
		}
	
	@Test public void tre() throws CharacterCodingException{
		Charset latin1 = Charset.forName("ISO-8859-1") ;	// 只能表示的英文字符
		CharsetEncoder encoder = latin1.newEncoder() ;	// 得到编码器
		CharsetDecoder decoder = latin1.newDecoder() ;	// 得到解码器
		// 通过CharBuffer类中的wrap方法将字符串变成CharBuffer类型
		CharBuffer cb = CharBuffer.wrap("北京MLDN软件实训中心！") ;
		ByteBuffer buf = encoder.encode(cb) ;	// 进行编码操作
		System.out.println(decoder.decode(buf)) ;	// 进行解码操作
	}
}
