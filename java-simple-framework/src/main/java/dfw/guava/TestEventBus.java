package dfw.guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;

/**
 * Created by fw on 2019/7/11
 */
public class TestEventBus {

    @Test
    public void demo(){
        EventBus bus = new EventBus();

        class Subscriber {
            @Subscribe
            public void a(Integer a){
                System.out.println("a = " + a);
            }
            @Subscribe
            public void b(Integer b){
                System.out.println("b = " + b);
            }
        }
        // 注册监听器
        bus.register(new Subscriber());

        // 发布事件
        bus.post(1);
        bus.post(1);
    }
}
