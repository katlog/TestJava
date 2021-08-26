package name.katlog.common.time;

import org.junit.Test;

import java.util.TimeZone;

/**
 * Created by fw on 2021/8/10
 */
public class TestTimeZone {

    @Test
    public void getDefault(){
        TimeZone timeZone = TimeZone.getDefault();
        System.out.println("timeZone = " + timeZone);
    }

    @Test
    public void getTimeZone(){

        TimeZone timeZone = TimeZone.getDefault();
        System.out.println("timeZone = " + timeZone);

        // 获取 “GMT+08:00”对应的时区
        TimeZone china = TimeZone.getTimeZone("GMT+:08:00");
        System.out.println("china = " + china);

        // 获取 “中国/重庆”对应的时区
        TimeZone chongqing = TimeZone.getTimeZone("Asia/Chongqing");
        System.out.println("chongqing = " + chongqing);
    }

    @Test
    public void getAvailableIDs(){
        String[] ids = TimeZone.getAvailableIDs();

        for (String id:ids) {
            System.out.println("id = " + id);
        }
    }
}
