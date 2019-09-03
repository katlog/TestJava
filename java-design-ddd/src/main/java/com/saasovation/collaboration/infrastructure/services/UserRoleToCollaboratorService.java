package com.saasovation.collaboration.infrastructure.services;

import com.saasovation.collaboration.domain.model.*;
import com.saasovation.collaboration.domain.model.collaborator.CollaboratorService;
import com.saasovation.collaboration.infrastructure.adaper.UserInRoleAdapter;
import com.saasovation.identityaccess.domain.model.identity.Tenant;

/**
 * Created by fw on 2019/8/31
 */
public class UserRoleToCollaboratorService implements CollaboratorService {

    public UserRoleToCollaboratorService() {
        super();
    }

    @Override
    public Author authorFrom(Tenant aTenant, String anIdentity) {
        return  (Author)UserInRoleAdapter
                .newInstance()
                .toCollaborator(
                        aTenant,
                        anIdentity,
                        "Author",
                        Author.class);
    }

    @Override
    public Creator creatorFrom(Tenant aTenant, String anIdentity) {
        return null;
    }

    @Override
    public Moderator moderatorFrom(Tenant aTenant, String anIdentity) {
        return null;
    }

    @Override
    public Owner ownerFrom(Tenant aTenant, String anIdentity) {
        return null;
    }

    @Override
    public Participant participantFrom(Tenant aTenant, String anIdentity) {
        return null;
    }
}
