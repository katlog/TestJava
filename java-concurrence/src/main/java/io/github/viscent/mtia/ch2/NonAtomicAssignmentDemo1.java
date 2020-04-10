package io.github.viscent.mtia.ch2;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * 本Demo必须使用32位Java虚拟机才能看到非原子操作的效果。
 * 运行本Demo时也可以指定虚拟机参数“-client”
 */
public class NonAtomicAssignmentDemo1 implements Runnable{
    static long value = 0;
    private final long valueToSet;
    public NonAtomicAssignmentDemo1(long valueToSet) {
        this.valueToSet = valueToSet;
    }

    public static void main(String[] args) {
        // 线程updateThread1将data更新为0
        Thread updateThread1 = new Thread(new NonAtomicAssignmentDemo1(0L));
        // 线程updateThread2将data更新为-1
        Thread updateThread2 = new Thread(new NonAtomicAssignmentDemo1(-1L));

        PrintStream ps = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                // 不实际进行输出
            }
        });

        updateThread1.start();
        updateThread2.start();

        // 共享变量value的快照（即瞬间值）
        long snapshot;
        while (0 == (snapshot = value) || -1 == snapshot) {
      /*
      * 这里，我们将“snapshot = value”放在循环内是为了不断地读取共享变量value的值。
      * 而server运行模式的Java虚拟机（的JIT编译器）在生成这段代码对应的机器码的时候，
      * 可能会进行循环优化（循环不变表达式外提）而将“snapshot = value”对应的机器码
      * 放在这个循环语句之外，这就使我们无法重复读取value的值。
      * 下面的输出语句并不是为了输出数据，而是为了防止JIT编译器做“循环不变表达式外提”这种优化。
      * 或者，将以下语句注释掉，以client模式运行本Demo也可以达到同样的效果——
      * 防止“循环不变表达式外提”优化。
      *
      * 循环不变表达式外提（Loop-invariant code motion）：
      * http://www.compileroptimizations.com/category/hoisting.htm
      */
            ps.print(snapshot);
        }

        System.err.printf("Unexpected data: %d(0x%016x)", snapshot, snapshot);
        ps.close();
        System.exit(0);
    }

    @Override
    public void run() {
        for (; ; ) {
            value = valueToSet;
        }
    }
}
