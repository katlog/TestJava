package name.katlog.designpattern.practice;

import lombok.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by fw on 2019/12/19
 */
public class TestObserver {


    @Value
  static  class Event{
        Long id;
        Date occurredTime;
        String desc;
    }

    interface EventHandler{
        void process(Event consumer);
    }

   static class PublishHandler{
        List<EventHandler> eventHandlers = new ArrayList<>();

        public void attach(EventHandler handler) {
            eventHandlers.add(handler);
        }

        public void detach(EventHandler eventHandler) {
            eventHandlers.remove(eventHandler);
        }

        public void publish(Event event) {
            for (EventHandler eventHandler : eventHandlers) {
                eventHandler.process(event);
            }
        }
    }

    public static void main(String[] args) {
        // observer.register
        PublishHandler publishHandler = new PublishHandler();

        publishHandler.attach(event -> System.out.println("consumer = " + event));
        publishHandler.attach(event -> System.out.println("consumer = " + event));
        publishHandler.attach(event -> System.out.println("consumer = " + event));

        publishHandler.publish(new Event(1L,new Date(),"dhhfhfh" ));
        publishHandler.publish(new Event(2L,new Date(),"DFJGJ" ));
        publishHandler.publish(new Event(2L,new Date(),"DFJGJ" ));
    }
}
