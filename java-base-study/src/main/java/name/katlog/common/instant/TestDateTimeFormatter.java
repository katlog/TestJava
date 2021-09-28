package name.katlog.common.instant;

import org.junit.Test;

import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;

/**
 * Created by fw on 2021/9/26
 */
public class TestDateTimeFormatter {

    @Test
    public void _staticFields() throws IllegalAccessException {
        for (Field field : DateTimeFormatter.class.getDeclaredFields()) {
            field.setAccessible(true);
            if(field.getType().equals(DateTimeFormatter.class)){
                System.out.println("field = " + field);
                Object value = field.get(null);
                System.out.println("value = " + value);

            }
        }
    }
}
