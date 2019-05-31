package name.katlog.ddd.event;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.Date;

/**
 * Created by fw on 2019/4/17
 */
public abstract class AbstractDomainEventSubscriber<T extends BaseEvent> implements DomainEventSubscriber<T> {

    @Getter(AccessLevel.PRIVATE)
    private Class<T> eventType;

    public AbstractDomainEventSubscriber(Class<T> eventType) {
        this.eventType = eventType;
    }

    @Override
    public void handleEvent(T aDomainEvent) {
        aDomainEvent.setOccurredTime(new Date());
        handle(aDomainEvent);
    }
    protected abstract void handle(T aDomainEvent);

    @Override
    public Class<T> eventType() {
        return getEventType();
    }
}
