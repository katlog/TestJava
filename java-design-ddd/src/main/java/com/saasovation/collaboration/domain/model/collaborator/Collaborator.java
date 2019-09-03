package com.saasovation.collaboration.domain.model.collaborator;

import lombok.AccessLevel;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by fw on 2019/8/31
 */
public abstract class Collaborator implements Serializable {
    @Setter(AccessLevel.PRIVATE)
    private String emailAddress;
    @Setter(AccessLevel.PRIVATE)
    private String identity;
    @Setter(AccessLevel.PRIVATE)
    private String name;

    public Collaborator(
            String anIdentity,
            String aName,
            String anEmailAddress) {

        super();
        this.setEmailAddress(anEmailAddress);
        this.setIdentity(anIdentity);
        this.setName(aName);
    }
    //...
}