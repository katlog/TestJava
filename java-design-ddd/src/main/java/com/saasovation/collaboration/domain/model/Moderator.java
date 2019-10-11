package com.saasovation.collaboration.domain.model;

import com.saasovation.collaboration.domain.model.collaborator.Collaborator;

/**
 * Created by fw on 2019/3/22
 */
public class Moderator extends Collaborator {
    public Moderator(String anIdentity, String aName, String anEmailAddress) {
        super(anIdentity, aName, anEmailAddress);
    }
}
