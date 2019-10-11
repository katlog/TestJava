package com.saasovation.collaboration.infrastructure.services;

import com.saasovation.collaboration.domain.model.*;
import com.saasovation.collaboration.domain.model.collaborator.CollaboratorService;
import com.saasovation.identityaccess.domain.model.identity.Tenant;

/**
 * Created by fw on 2019/8/31
 *
 * CollaboratorService、UserInRoleAdaptor和CollaboratorTranslator便组成了一个防腐层
 */
public class UserRoleToCollaboratorService implements CollaboratorService {

    public UserRoleToCollaboratorService() {
        super();
    }

    /** chapter 13 集成限界上下文：使用防腐层实现REST客户端 */
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
