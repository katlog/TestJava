package com.saasovation.collaboration.domain.model.collaborator;

import com.saasovation.collaboration.domain.model.*;
import com.saasovation.identityaccess.domain.model.identity.Tenant;
import com.saasovation.supply.FactoryMethod;

import static com.saasovation.supply.FactoryMethod.Source.DomainService;


/**  chapter  11  工厂  该领域服务类将身份与访问上下文中的对象翻译成协作上下文中的对象。*/
public interface CollaboratorService {

    @FactoryMethod(from = DomainService)
    public Author authorFrom(Tenant aTenant, String anIdentity);

    @FactoryMethod(from = DomainService)
    public Creator creatorFrom(Tenant aTenant, String anIdentity);

    @FactoryMethod(from = DomainService)
    public Moderator moderatorFrom(Tenant aTenant, String anIdentity);

    @FactoryMethod(from = DomainService)
    public Owner ownerFrom(Tenant aTenant, String anIdentity);

    @FactoryMethod(from = DomainService)
    public Participant participantFrom(Tenant aTenant, String anIdentity);
}
