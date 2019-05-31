package com.saasovation.agilepm.domain.model;

/**
 * todo 待更正
 * Created by fw on 2019/3/18
 */
public interface DomainEventSubscriber<T> {

    Class<?> subscribedToEventType();

    void handleEvent(T aDomainEvent);

}
