package name.katlog.jvm.item10;

import java.util.*;

/**
 * Created by fw on 2018/4/26
 */
public class Sugar {

    /**
     * 代码清单10-2 泛型擦除前的例子
     */
    static class C1 {
        public static void main(String[] args) {
            Map<String, String> map = new HashMap();
            map.put("hello", "你好");
            map.put("how are you?", "吃了没？");
            System.out.println(map.get("hello"));
            System.out.println(map.get("how are you?"));
        }
    }

    /**
     * 代码清单10-3 泛型擦除后的例子
     */
    static class C2 {
        public static void main(String[] args) {
            Map map = new HashMap();
            map.put("hello", "你好");
            map.put("how are you?", "吃了没？");
            System.out.println((String) map.get("hello"));
            System.out.println((String) map.get("how are you?"));
        }
    }

    /**
     * 代码清单10-4 当泛型遇见重载1
     */
    static class GenericTypes {
        // public static void method(List<String> list) {
        //     System.out.println("invoke _03method(List<String>list)");
        // }

        public static void method(List<Integer> list) {
            System.out.println("invoke _03method(List<Integer>list)");
        }
    }

    /**
     * 代码清单10-5 当泛型遇见重载2
     */
    static class GenericTypes2 {
       // public static String method(List<String> list) {
       //     System.out.println("invoke _03method(List<String>list)");
       //     return "";
       // }

        public static int method(List<Integer> list) {
            System.out.println("invoke _03method(List<Integer>list)");
            return 1;
        }

        public static void main(String[] args) {
            // method(new ArrayList<String>());
            method(new ArrayList<Integer>());
        }
    }


    static class Orther1 {
        /**
         * 代码清单10-6 自动装箱、 拆箱与遍历循环
         */
        public static void main(String[] args) {
            List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
            //如果在JDK 1.7中,还有另外一颗语法糖[1]
            //能让上面这句代码进一步简写成
           // List<Integer> list2 = [1, 2, 3, 4]; 【8不行】
            int sum = 0;
            for (int i : list1) {
                sum += i;
            }
            System.out.println(sum);
        }
    }

    static class Orther2 {
        /**
         * 代码清单10-7 自动装箱、 拆箱与遍历循环编译之后
         */
        public static void main(String[] args) {
            List list = Arrays.asList(new Integer[]{
                    Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4)});
            int sum = 0;
            for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ) {
                int i = ((Integer) localIterator.next()).intValue();
                sum += i;
            }
            System.out.println(sum);
        }
    }

    static class AutoBoxTrap {
        /**
         * 	代码清单10-8 自动装箱的陷阱
         */
        public static void main(String[] args) {
            Integer a = 1;
            Integer b = 2;
            Integer c = 3;
            Integer d = 3;
            Integer e = 321;
            Integer f = 321;
            Long g = 3L;
            System.out.println(c == d);               // t
            System.out.println(e == f);               // f
            System.out.println(c == (a + b));           // t
            System.out.println(c.equals(a + b));      // t
            System.out.println(g == (a + b));           // t
            System.out.println(g.equals(a + b));      // f
        }


    }

    static class ConditionalCompilation1 {
        /**
         * 代码清单10-9 Java语言的条件编译
         */
        public static void main(String[] args) {
            if (true) {
                System.out.println("block 1");
            } else {
                System.out.println("block 2");
            }
        }
    }

    static class ConditionalCompilation2 {
        public static void main(String[] args) {
            //编译器将会提示“Unreachable code”
           // while (false) {
           //     System.out.println("");
           // }
        }


    }

}
