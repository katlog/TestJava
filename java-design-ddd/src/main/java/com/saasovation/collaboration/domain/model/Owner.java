package com.saasovation.collaboration.domain.model;

import com.saasovation.collaboration.domain.model.collaborator.Collaborator;

/**
 * Created by fw on 2019/3/22
 */
public class Owner extends Collaborator {
    public Owner(String name, String fullName, String email) {
        super(name,fullName, email);
    }
}
