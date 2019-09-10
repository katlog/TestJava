package com.saasovation.identityaccess.domain.model;

import com.saasovation.identityaccess.domain.model.identity.TenantId;
import com.saasovation.identityaccess.domain.model.identity.User;

public interface UserRepository {
    User userFromAuthenticCredentials(TenantId aTenantId, String aUsername, String encryptedPassword);

    void add(User user);
}
