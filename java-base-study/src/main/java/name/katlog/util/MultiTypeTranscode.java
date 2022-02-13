package name.katlog.util;


import jdk.internal.util.Preconditions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 选取的扩充拉丁字母范围 0x100～0x200 一共256个
 */
public final class MultiTypeTranscode {

    public static final int BIT_SUM_NUMS = 128;

    private MultiTypeTranscode() {
    }


    /** 转换的字符 起始值 Ā */
    private static final char START_CHAR = 0x100;

    /** 转换的字符 结束值 ǿ */
    private static final char END_CHAR = 0x200-1;

    /** 每个字符代表的 bit 长度 */
    private static final int CHAR_BIT_LEN = 8;



    /**
     * typeCodeFun 转换成拉丁扩充字母代表的 code
     *      eg
     *          [BUYER_TYPE_ONE_NEW_ORDER_SELLER_ACCOUNT_FREE, BUYER_TYPE_NINE_NEW_ORDER_CAN_NOT_CONTACT]
     *          其 uniqueCode 分别为 7、16
     *          先转换成128位的二进制字符串：00000010 00000001 00...， 第7、16位为1，其余为0
     *          再将二进制字符串每 8 位转换成字符
     *              ，如 00000010 表示10进制的2，从START_CHAR开始的第2位为Ă
     *              ，00000001 表示10进制的1，从START_CHAR开始的第1位为ā
     *              ，00000000 即表示 START_CHAR 本身Ā
     *          最终转换成 ĂāĀĀĀĀĀĀĀĀĀĀĀĀĀĀ
     *
     * @param typeCodes 类型对应的 code
     * @return
     */
    public static <T> String subType2Code(Set<Integer> typeCodes ,int binary) {

        if (binary > 10) {
            throw new IllegalArgumentException("binary beyond 10");
        }

        int max = Integer.MIN_VALUE;
        for (Integer typeCode : typeCodes) {
            max = Math.max(max, typeCode);
        }

        char[] res = new char[max / binary + 1];
        int step = 0;

        boolean[] bitBool = new boolean[max];

        for (Integer uniqueCode : typeCodes) {
            bitBool[uniqueCode - 1] = true;
        }

        // 每 binary 位转换成 int值 ，再加上 START_CHAR 转换成最终字符

        int val = 0;
        for (int cur = 0; cur < bitBool.length ; cur++) {

            val = (val << 1) | (bitBool[cur] ? 1 : 0);

            if ((cur + 1) % binary == 0) {
                char c = (char) (val + START_CHAR);
                val = 0;
                res[step++] = c;
            }
        }

        if (val != 0) {
            char c = (char) (val + START_CHAR);
            res[step++] = c;
        }

        return new String(res);
    }

    public static <T> String subType2Code(Set<T> typeCodes ,int binary,Function<T,Integer> type2intFun) {
        return subType2Code(typeCodes.stream().map(type2intFun).collect(Collectors.toSet()), binary);
    }

    /**
     * 将对应的 code 转换成 TypeEnum
     *      先将每个字符转换成 8 为的bit字符串，如 Ă 转换成 00000010
     *      拼接起来后，bit位为1的位置表示对应的 uniqueCode
     *      如 00000010 00000001 00.. 表示两个uniqueCode [7, 16]
     *      再找到对应 uniqueCode 的 TypeEnum 的值并返回
     *
     * @param code 由 0x100～0x1FF 之间的字符表示
     *
     * @return Set<T>
     */
    public static <T> Set<T> code2SubType(String code,Function<Integer,T> code2Type,int binary) {

        int cur = 0;

        int step = 0;
        Set<Integer> codes = new HashSet<>();

        char[] chars = code.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            int diff = c - START_CHAR;
            while (cur++ % binary != 0) {
                // 最后1位是1
                if ((diff & -diff) == 1) {
                    codes.add(cur);
                }
            }
        }


        Set<T> res = new HashSet<>(codes.size());
        for (int uniqueCode : codes) {
            T subTypeEnum = code2Type.apply(uniqueCode);
            if (subTypeEnum != null) {
                res.add(subTypeEnum);
            }
        }

        return res;
    }


    public static void main(String[] args) {
        Set<DemoTypeEnum> enums;

        Function<Integer, DemoTypeEnum> code2Type = DemoTypeEnum::codeOf;
        enums = code2SubType("Ā", code2Type,8); // 0
        System.out.println("enums = " + enums);

        enums = code2SubType("ǿ", code2Type,8); // 11111111
        System.out.println("enums = " + enums);

        enums = code2SubType("Ăā", code2Type,8); // 10  1
        System.out.println("enums = " + enums);

        String s = subType2Code(enums,8,DemoTypeEnum::getCode);
        System.out.println("s = " + s);

        Set<DemoTypeEnum> typeEnums = code2SubType(s, code2Type, 8);
        System.out.println("typeEnums = " + typeEnums);

//        for (int i = 256; i < 512; i++) {
//            System.out.println("i = " + i + "\t" + (char) i);
//        }

    }

    public enum DemoTypeEnum{
        ;
        int code;

        public int getCode() {
            return code;
        }

        public static DemoTypeEnum codeOf(int code) {
            for (DemoTypeEnum value : values()) {
                if (value.code==code) {
                    return value;
                }

            }
            return null;
        }
    }

}
