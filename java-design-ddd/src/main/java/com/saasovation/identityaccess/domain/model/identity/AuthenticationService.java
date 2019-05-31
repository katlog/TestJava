package com.saasovation.identityaccess.domain.model.identity;

import com.saasovation.supply.DomianService;

@DomianService
public interface AuthenticationService {

    public UserDescriptor authenticate(TenantId aTenantId, String aUsername, String aPassword);

}
