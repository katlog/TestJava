package name.katlog.designpattern.practice;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

/**
 * Created by fw on 2019/6/11
 */
public class ChainOfResponsibility {

    interface Handler{
        void operation();
    }
    abstract class AbstractHandler implements Handler{
        @Getter(AccessLevel.PROTECTED)
        private Handler handler;
        public AbstractHandler(Handler handler) {
            this.handler = handler;
        }
    }

    class MyHandler extends AbstractHandler{
        public MyHandler(Handler handler) {
            super(handler);
        }
        @Override
        public void operation() {
            System.out.println("handler = " + getHandler());
            getHandler().operation();
        }
    }

    @Test
    public void tests(){
        Handler handler0 = new Handler() {
            @Override
            public void operation() {
                System.out.println("true = " + true);
            }
        };
        Handler handler1 = new MyHandler(handler0);
        Handler handler2 = new MyHandler(handler1);

        handler2.operation();
    }
}
