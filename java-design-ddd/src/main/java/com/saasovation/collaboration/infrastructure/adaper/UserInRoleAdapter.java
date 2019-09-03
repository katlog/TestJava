package com.saasovation.collaboration.infrastructure.adaper;

import com.saasovation.collaboration.domain.model.Author;
import com.saasovation.collaboration.domain.model.collaborator.Collaborator;
import com.saasovation.identityaccess.domain.model.identity.Tenant;

/**
 * Created by fw on 2019/8/31
 */
public class UserInRoleAdapter {
    private UserInRoleAdapter() {
    }


    public static UserInRoleAdapter newInstance() {
        return new UserInRoleAdapter();
    }

    public Collaborator toCollaborator(Tenant aTenant, String anIdentity, String author, Class<Author> authorClass) {
        return null;
    }
}
