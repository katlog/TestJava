package name.katlog.util;

/**
 * Created by fw on 2019/7/29
 */
public final class JsonUtil {

    public static boolean validator(String string) {
        if (string == null || string.length() < 1) {
            return false;
        }
        return new JsonValidator().validate(string);
    }

}
