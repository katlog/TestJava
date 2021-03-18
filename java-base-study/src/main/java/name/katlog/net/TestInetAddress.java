package name.katlog.net;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by fw on 2019/2/25
 */
public class TestInetAddress {

    @Test
    public void getByName() throws IOException {
        InetAddress baidu = InetAddress.getByName("www.baidu.com");
        System.out.println("baidu = " + baidu);
    }

    @Test
    public void getAllByName() throws UnknownHostException {
        InetAddress[] baidu = InetAddress.getAllByName("ewxtongzhen.58.com");
        Arrays.stream(baidu).forEach(System.out::println);
    }

    @Test
    /** 为本地主机创建一个InetAddress对象 */
    public void getLocalHost() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localHost = " + localHost);
    }

}
