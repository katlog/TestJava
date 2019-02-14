package org.person.dfw.util;


/**
 * 变量名、函数名 工具
 */
public final class NameUtils {
    private NameUtils() {
    }


    private static final Character A = 'A';
    private static final Character Z = 'Z';
    private static final Character a = 'a';
    private static final Character z = 'z';

    private static final Character UNDER_LINE = '_';


    /**
     * 下划线转驼峰
     *
     * @param from
     * @return
     */
    public static String underLine2Camel(String from) {
        StringBuilder sb = new StringBuilder(from);
        StringBuilder result = new StringBuilder(from.length());
        boolean meetSym = false;

        for (int i = 0; i < sb.length(); ++i) {
            char c = sb.charAt(i);
            if (c == UNDER_LINE) {
                meetSym = true;
            } else {
                if (!meetSym) {
                    result.append(c);
                } else {
                    result.append(c >= a && c <= z ? (char) (c - 32) : c);
                }

                meetSym = false;
            }
        }

        return result.toString();
    }


    private static boolean isEmpty(String str) {
        return str == null || str.equals("") || str.trim().equals("");
    }

    /**
     * 将字符串首字母转大写
     *
     * @param str 需要处理的字符串
     */
    public static String firstCharUpper(String str) {
        if (isEmpty(str)) {
            return "";
        }
        char[] cs = str.toCharArray();
        if ((cs[0] >= a) && (cs[0] <= z)) {
            cs[0] -= (char) 0x20;
        }
        return String.valueOf(cs);
    }

    /**
     * 将字符串首字母转小写
     *
     * @param str
     * @return
     */
    public static String firstCharLower(String str) {
        if (isEmpty(str)) {
            return "";
        }
        char[] cs = str.toCharArray();
        if ((cs[0] >= A) && (cs[0] <= Z)) {
            cs[0] += (char) 0x20;
        }
        return String.valueOf(cs);
    }


    /**
     * 驼峰转下划线
     *
     * @param from
     * @return
     */
    public static String camel2UnderLine(String from) {
        StringBuilder sb = new StringBuilder(from);
        StringBuilder result = new StringBuilder(from.length());

        for (int i = 0; i < sb.length(); ++i) {
            char c = sb.charAt(i);
            if (c >= A && c <= Z) {
                result.append(i == 0 ? "" : '_').append((char) (c + 32));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
