package name.katlog.ddd.event;

import org.junit.Test;

import java.util.Date;

/**
 * Created by fw on 2019/4/17
 */
public class TestEvent {

    @Test
    public void testEvent(){


        DomainEventPublisher instance = DomainEventPublisher.instance();
        instance.subscribe(new DomainEventSubscriber<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent aDomainEvent) {
                Object source = aDomainEvent.getSource();
                System.out.println("source = " + source);
                Date ocurreTime = aDomainEvent.getOccurredTime();
                System.out.println("occurredTime = " + ocurreTime);
            }

            @Override
            public Class<BaseEvent> eventType() {
                return BaseEvent.class;
            }
        });

        instance.subscribe(new AbstractDomainEventSubscriber<BaseEvent>(BaseEvent.class) {
            @Override
            protected void handle(BaseEvent aDomainEvent) {
                Object source = aDomainEvent.getSource();
                System.out.println("source111 = " + source);
                Date ocurreTime = aDomainEvent.getOccurredTime();
                System.out.println("occurredTime = " + ocurreTime);
            }
        });

        instance.publish(new BaseEvent<>(123123));


    }
}
