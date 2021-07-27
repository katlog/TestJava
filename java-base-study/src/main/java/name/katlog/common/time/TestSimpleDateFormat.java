package name.katlog.common.time;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by fw on 2021/7/27
 */
public class TestSimpleDateFormat {

    @Test
    public void parse() throws ParseException {


        System.out.println(new SimpleDateFormat("ddHH:mm:ss yyyy", Locale.ENGLISH)
                .parse("2713:16:33 2019"));

        // 有英文星期EEE、英文月MMM、时区zzz
        System.out.println(new SimpleDateFormat("EEEMMMddHH:mm:sszzzyyyy", Locale.ENGLISH)
                .parse("ThuSep2610:21:46CST2019"));
    }
}
