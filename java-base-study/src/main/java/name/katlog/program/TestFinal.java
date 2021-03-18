package name.katlog.program;

import lombok.AllArgsConstructor;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * Created by fw on 2020/7/20
 */
public class TestFinal {

    @Test
    public void common() {

        // 写法上可以有多次，实际只能被赋值一次
        final int i;
        if (new Random().nextBoolean()) {
            i = 1;
        } else {
            i = 2;
        }
    }

    @AllArgsConstructor
    class RT{
        final String con;
    }

    @Test
    public void _reflect() throws NoSuchFieldException {
        RT rt = new RT("A");
        Field con = rt.getClass().getDeclaredField("con");
        con.setAccessible(true);
        try {
            // 反射不能赋值
            con.set(con, "B");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
