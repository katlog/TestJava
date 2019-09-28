package com.saasovation.collaboration.infrastructure.services;

import com.saasovation.collaboration.domain.model.collaborator.Collaborator;
import com.saasovation.common.media.RepresentationReader;

import java.lang.reflect.Constructor;

/**
 * Created by fw on 2019/9/10
 */
public class CollaboratorTranslator {
    public CollaboratorTranslator() {
        super();
    }
    public <T extends Collaborator> T toCollaboratorFromRepresentation(
            String aUserInRoleRepresentation,
            Class<T> aCollaboratorClass)
            throws Exception {

        RepresentationReader reader =
                new RepresentationReader(aUserInRoleRepresentation);
        String username = reader.stringValue("username");
        String firstName = reader.stringValue("firstName");
        String lastName = reader.stringValue("lastName");
        String emailAddress = reader.stringValue("emailAddress");
        T collaborator =
                this.newCollaborator(
                        username,
                        firstName,
                        lastName,
                        emailAddress,
                        aCollaboratorClass);
        return collaborator;
    }
    private <T extends Collaborator> T newCollaborator(
            String aUsername,
            String aFirstName,
            String aLastName,
            String aEmailAddress,
            Class<T> aCollaboratorClass)
            throws Exception {

        Constructor<T> ctor =
                aCollaboratorClass.getConstructor(
                        String.class, String.class, String.class);
        T collaborator =
                ctor.newInstance(
                        aUsername,
                        (aFirstName + " " + aLastName).trim(),
                        aEmailAddress);
        return collaborator;
    }
}