package org.person.dfw.util;

public final class NameUtils {
    private NameUtils() {
    }


    /** 下划线转驼峰
     * @param from
     * @return
     */
    public static String underLine2Camel(String from) {
        StringBuilder sb = new StringBuilder(from);
        StringBuilder result = new StringBuilder(from.length());
        boolean meetSym = false;

        for(int i = 0; i < sb.length(); ++i) {
            char c = sb.charAt(i);
            if (c == '_') {
                meetSym = true;
            } else {
                if (!meetSym) {
                    result.append(c);
                } else {
                    result.append(c > '`' && c < '{' ? (char)(c - 32) : c);
                }

                meetSym = false;
            }
        }

        return result.toString();
    }

    /** 首字符变小写
     * @param string
     * @return
     */
    public static String firstCharLower(String string) {
        StringBuilder sb = new StringBuilder(string);

        char firstChar = string.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            sb.delete(0, 1).insert(0, (char)(firstChar + 32));
            return sb.toString();
        }else {
            return string;
        }
    }

    /** 首字符大写
     * @param string
     * @return
     */
    public static String firstCharCapital(String string) {
        StringBuilder sb = new StringBuilder(string);

        char firstChar = string.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            sb.delete(0, 1).insert(0, (char)(firstChar - 32));
            return sb.toString();
        }else {
            return string;
        }
    }

    /** 驼峰转下划线
     * @param from
     * @return
     */
    public static String camel2UnderLine(String from) {
        StringBuilder sb = new StringBuilder(from);
        StringBuilder result = new StringBuilder(from.length());

        for(int i = 0; i < sb.length(); ++i) {
            char c = sb.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                result.append(i == 0 ? "" : '_').append((char)(c + 32));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
