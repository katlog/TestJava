package name.katlog.ddd.event;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fw on 2019/3/18
 */
public class DomainEventPublisher {

    @SuppressWarnings("unchecked")
    // private static final ThreadLocal<List> subscribers = new ThreadLocal<List>();
    private static final ThreadLocal<Map<Class<?>,List<DomainEventSubscriber<BaseEvent>>>> subscribers = new ThreadLocal<>();


    private static final ThreadLocal<Boolean> publishing = new ThreadLocal<Boolean>() {
        protected Boolean initialValue() {
            return Boolean.FALSE;
        }
    };
    public static DomainEventPublisher instance() {
        return new DomainEventPublisher();
    }
    private DomainEventPublisher() {
        super();
    }
    @SuppressWarnings("unchecked")
    public <T extends BaseEvent> void publish(final T aDomainEvent) {
        if (publishing.get()) {
            return;
        }
        try {
            publishing.set(Boolean.TRUE);
            List<DomainEventSubscriber<BaseEvent>> registeredSubscribers = subscribers.get().get(aDomainEvent.getClass());
            if (registeredSubscribers != null) {
                for (DomainEventSubscriber subscriber : registeredSubscribers) {
                    subscriber.handleEvent(aDomainEvent);
                }
            }
        } finally {
            publishing.set(Boolean.FALSE);
        }
    }
    public DomainEventPublisher reset() {
        if (!publishing.get()) {
            subscribers.set(null);
        }
        return this;
    }
    @SuppressWarnings("unchecked")
    public <T extends BaseEvent> void subscribe(DomainEventSubscriber<BaseEvent> aSubscriber) {
        if (publishing.get()) {
            return;
        }
        Map<Class<?>, List<DomainEventSubscriber<BaseEvent>>> registeredSubscribersMap = subscribers.get();
        if (registeredSubscribersMap == null) {
            registeredSubscribersMap = new HashMap<>();
            subscribers.set(registeredSubscribersMap);
        }
        List<DomainEventSubscriber<BaseEvent>> registeredSubscribers =
                registeredSubscribersMap.computeIfAbsent(aSubscriber.eventType(), k -> new ArrayList<>());

        registeredSubscribers.add(aSubscriber);
    }
}
