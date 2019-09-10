package com.saasovation.collaboration;

import com.saasovation.collaboration.infrastructure.Entity;
import com.saasovation.collaboration.infrastructure.ValidationNotificationHandler;
import com.saasovation.collaboration.infrastructure.WarbleValidator;

public class Warble  extends Entity {

    @Override
    public void validate(ValidationNotificationHandler aHandler) {
        (new WarbleValidator(this, aHandler)).validate();
    }
}
