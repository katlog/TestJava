package com.saasovation.collaboration.infrastructure;

public abstract class Entity extends IdentifiedDomainObject {
    public Entity() {
        super();
    }
    public void validate(
            ValidationNotificationHandler aHandler) {
    }
}