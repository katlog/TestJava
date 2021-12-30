package name.katlog.program;

import org.junit.Test;

/**
 * Created by fw on 2021/6/30
 */
public class TestPassBy {

    // 值传递
    @Test
    public void valuePassBy(){

        // 基本类型
        int num = 10;
        changeNum(num);
        System.out.println("num = " + num);

        /**  注意++i和i++在传递时的影响 */
        changeNum(num++);
        System.out.println("num = " + num);

        changeNum(++num);
        System.out.println("num = " + num);

        // 第二个例子：没有提供改变自身方法的引用类型
        String str = "before";
        changeStr(str);
        System.out.println("str = " + str);

        // 第三个例子：提供了改变自身方法的引用类型
        StringBuilder sb = new StringBuilder("iphone");
        changeSb(sb);
        System.out.println("sb = " + sb);


        // 第四个例子：提供了改变自身方法的引用类型，但是不使用，而是使用赋值运算符。
        StringBuilder sb1 = new StringBuilder("iphone");
        changeSb1(sb1);
        System.out.println("sb1 = " + sb1);
    }

    private void changeNum(int num) {
        System.out.println("num pass by = " + num);
        num = num * 3;
        System.out.println("num changed = " + num);
    }

    private void changeStr(String str){
        System.out.println("str pass by = " + str);
        str = "after";
        System.out.println("str changed = " + str);
    }

    private void changeSb(StringBuilder builder) {
        System.out.println("sb pass by = " + builder);
        builder.append("4");
        System.out.println("sb changed = " + builder);
    }


    private void changeSb1(StringBuilder builder) {
        System.out.println("sb1 pass by = " + builder);
        builder = new StringBuilder("ipad");
        System.out.println("sb1 changed = " + builder);
    }
}
