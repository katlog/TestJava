package name.dfw.jvm.bytecode;

/**
 * Created by dell on 2018/5/4
 */
public class ByteCode {

    // load
    final String names[] = {"robin", "hb"};

    public void load() {
        String str = names[0];
    }

    // store
    public void store() {
        int moneys[] = new int[5];
        moneys[1] = 100;
    }


    // 数学操作系列

    public void operate1() {
        int k = 100;
        k = k >> 1;
    }

    // 自增、自减
    public void self() {
        int d = 10;
        d++;
        d += 2;
        d--;
    }

    // 比较指令
    public void compare() {
        long a = 11;
        long b = 10;
        boolean result = (a > b);
    }

    // 有条件跳转指令
    public void goto1() {
        int a = 11;
        int b = 10;
        boolean result = (a > b);
        if (result)
            a += 2;
        if (!result)
            a += 2;
        if (a > 0)
            a--;
    }

    // 有条件跳转指令
    public void goto2() {
        int i = 0;
        Object obj = new Object();
        if (obj == null) {
            i = 0;
        }
        if (obj != null) {
            i = 1;
        }
    }

    // new和数组
    public void newAndarray() {
        int ids[]=new int[5];
        Object objs[]=new Object[5];
        Object obj=new Object();
        Hello hello=new Hello();
        int len=objs.length;
    }

    class Hello{}

    // 异常处理器
    public static void sleep(long d) {
        try {
            Thread.sleep(d);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
