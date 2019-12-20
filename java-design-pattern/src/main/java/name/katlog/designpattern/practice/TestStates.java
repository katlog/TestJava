package name.katlog.designpattern.practice;

import lombok.AccessLevel;
import lombok.Setter;

/**
 * Created by fw on 2019/12/20
 */
public class TestStates {

  static abstract class States{
      @Setter(AccessLevel.PROTECTED)
      private States nextStage;
      abstract void handle(Context context);
    }
    static class ConcreteStateA extends States{
        @Override
        public void handle(Context context) {
            System.out.println("process state a....,next...b" );
            context.setStates(new ConcreteStateB());
        }
    }
    static class ConcreteStateB extends States{
        @Override
        public void handle(Context context) {
            System.out.println("process state b....,next...c" );
            context.setStates(new ConcreteStateC());
        }
    }

    static class ConcreteStateC extends States{
        @Override
        public void handle(Context context) {
            System.out.println("process state c....,next...a" );
            context.setStates(new ConcreteStateA());
        }
    }
    static class Context{
      @Setter
      private States states;
      public Context(States states) {
            this.states = states;
      }
      public void request(){
         states.handle(this);
      }
    }

    public static void main(String[] args) {
        Context context = new Context(new ConcreteStateA());

        context.request();
        context.request();
        context.request();
        context.request();
        context.request();
        context.request();
        context.request();
    }
}
