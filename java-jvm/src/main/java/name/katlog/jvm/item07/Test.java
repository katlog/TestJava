package name.katlog.jvm.item07;

public class Test {
    static {
        i = 0;//给变量赋值可以正常编译通过
//        System.out.print(i);//这句编译器会提示"非法向前引用"
    }
    static int i = 1;
}