package name.katlog.net;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by fw on 2019/5/10
 */
public class UrlEncodeDecodeTest {

    @Test
    public void encode(){
        String encode = URLEncoder.encode("AACCC=//邸katlog");
        System.out.println("encode = " + encode);
    }

    @Test
    public void encode1() throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("AACCC=//邸katlog");
        System.out.println("encode = " + encode);

        String encode1 = URLEncoder.encode("AACCC=//邸katlog","UTF-8");
        System.out.println("encode = " + encode1);

        String encode2 = URLEncoder.encode("AACCC=//邸katlog","ASCII");
        System.out.println("encode = " + encode2);

        String encode3 = URLEncoder.encode("AACCC=//邸katlog","gbk");
        System.out.println("encode = " + encode3);
    }


}
