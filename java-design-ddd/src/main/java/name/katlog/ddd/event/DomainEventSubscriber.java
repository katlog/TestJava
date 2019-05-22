package name.katlog.ddd.event;

/**
 * Created by fw on 2019/3/18
 */
public interface DomainEventSubscriber<T extends BaseEvent> {
    void handleEvent(T aDomainEvent);
    Class<T> eventType();
}
