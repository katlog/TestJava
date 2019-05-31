package com.saasovation.collaboration.domain.model.collaborator;

import com.saasovation.collaboration.domain.model.*;
import com.saasovation.identityaccess.domain.model.identity.Tenant;


public interface CollaboratorService {

    public Author authorFrom(Tenant aTenant, String anIdentity);

    public Creator creatorFrom(Tenant aTenant, String anIdentity);

    public Moderator moderatorFrom(Tenant aTenant, String anIdentity);

    public Owner ownerFrom(Tenant aTenant, String anIdentity);

    public Participant participantFrom(Tenant aTenant, String anIdentity);
}
