package name.katlog.designpattern.practice;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fw on 2019/6/11
 */
public class Observer {

    class Event{
        @Getter
        int order;
        public Event(int order) {
            this.order = order;
        }
    }
    interface EventHandler{
        void handle(Event event);
    }
    static class EventPublisher {
        private static final EventPublisher INSTANCE = new EventPublisher();
        private Map<Class<?>,List<EventHandler>> subscribers = new HashMap<>();

        private EventPublisher() {
        }
        public static EventPublisher getInstance(){
            return INSTANCE;
        }

        <T extends Event> void register(Class<T> eventClass, EventHandler handler) {

            // 旧方式
            // List<EventHandler> handlers = subscribers.get(eventClass);
            // if (handlers != null) {
            //     handlers.add(handler);
            // } else {
            //     handlers = new ArrayList<>();
            //     handlers.add(handler);
            //     subscribers.put(eventClass, handlers);
            // }

            // 新方式
            // subscribers.computeIfPresent(eventClass, (aClass, eventHandlers) -> {
            //     eventHandlers.add(handler);
            //     return eventHandlers;
            // });
            // subscribers.putIfAbsent(eventClass, Lists.newArrayList(handler));

            // 最佳方式
            subscribers.merge(eventClass, Lists.newArrayList(handler), (eventHandlers, eventHandlers2) -> {
                eventHandlers.add(eventHandlers2.get(0));
                return eventHandlers;
            });
        }

        void publish(Event e){
            subscribers.computeIfPresent(e.getClass(), (aClass, eventHandlers) -> {
                eventHandlers.forEach(handler -> handler.handle(e));
                return eventHandlers;
            });

            // subscribers.forEach((aClass, eventHandlers) -> {
            //     eventHandlers.forEach(handler -> handler.handle(e));
            // });
        }
    }


    @Test
    public void test(){

        System.out.println(3 | 9);
        EventPublisher instance = EventPublisher.getInstance();
        // instance.register(Event.class, new EventHandler() {
        //     @Override
        //     public <T extends Event> void handle(T event) {
        //         System.out.println("event = " + event.getOrder());
        //     }大话设计模式
        // });

        instance.register(Event.class, event -> System.out.println("event = " + event));

        instance.publish(new Event(1));
        instance.publish(new Event(2));
        instance.publish(new Event(3));
    }
}
