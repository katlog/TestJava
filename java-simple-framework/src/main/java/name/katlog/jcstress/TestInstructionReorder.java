package name.katlog.jcstress;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.I_Result;

/**
 * Created by fw on 2021/11/8
 */
@JCStressTest // 标记此类为一个并发测试类
@Outcome(id = {"0"}, expect = Expect.ACCEPTABLE_INTERESTING, desc = "wrong result") // 描述测试结果
@Outcome(id = {"-1", "5"}, expect = Expect.ACCEPTABLE, desc = "normal result") // 描述测试结果
@State //标记此类是有状态的
public class TestInstructionReorder {

    private boolean flag ;
    private int x;

    public TestInstructionReorder() {}

    @Actor
    public void actor1(I_Result r) {
        if (flag) {
            r.r1 = x;
        } else {
            r.r1 = -1;
        }
    }

    @Actor
    public void actor2(I_Result r) {
        this.x = 5;
        flag = true;
    }
}