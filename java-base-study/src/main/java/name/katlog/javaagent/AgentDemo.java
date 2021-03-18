package name.katlog.javaagent;

import java.lang.instrument.Instrumentation;

/**
 * <a href='https://www.jianshu.com/p/80d13612438c'>java agent探针技术</a>
 * Created by fw on 2021/1/6
 */
public class AgentDemo {

    private static Instrumentation instrumentation;

    /**
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中
     *
     * @param agentArgs 是 premain 函数得到的程序参数，随同 “– javaagent”一起传入。与 main 函数不同的是，
     *                  这个参数是一个字符串而不是一个字符串数组，如果程序参数有多个，程序将自行解析这个字符串。
     *
     * @param inst   java.lang.instrument.Instrumentation的实例，由JVM自动传入。Instrumentation是接口，
     *               集中了其中几乎所有的功能方法，例如类定义的转换和操作等等。
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("=========premain方法执行1========");
        System.out.println(agentArgs);

        instrumentation = inst;

        SimpleClassTransformer transformer = new SimpleClassTransformer();
        inst.addTransformer(transformer);
    }

    /**
     * 如果不存在 premain(String agentArgs, Instrumentation inst)
     * 则会执行 premain(String agentArgs)
     *
     * @param agentArgs
     */
    public static void premain(String agentArgs) {
        System.out.println("=========premain方法执行2========");
        System.out.println(agentArgs);
    }

}
