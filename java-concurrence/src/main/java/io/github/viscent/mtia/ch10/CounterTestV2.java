package io.github.viscent.mtia.ch10;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Arbiter;
import org.openjdk.jcstress.annotations.Description;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.LongResult1;

// @JCStressTest
@State
@Description("测试Counter的线程安全性")
@Outcome(id = "[2]", expect = Expect.ACCEPTABLE, desc = "OK")
@Outcome(id = "[1]", expect = Expect.FORBIDDEN, desc = "丢失更新或者读脏数据")
public class CounterTestV2 {
    final Counter counter = new Counter();
    // @Actor
    // public void actor1() {
    //     counter.increment();
    // }
    //
    // @Actor
    // public void actor2() {
    //     counter.increment();
    // }
    //
    // @Arbiter
    // public void actor3(LongResult1 r) {
    //     r.r1 = counter.vaule();
    // }
}
