package name.katlog.dddimpl.assist;

import java.util.logging.Handler;

/**
 * Created by fw on 2019/2/11
 */
public class DomainEventPublisher {

    private static DomainEventPublisher instance = new DomainEventPublisher();

    public static DomainEventPublisher instance() {
        return instance;
    }

    public void publish(DomainEvent event){

    }
}
