package com.saasovation.identityaccess.domain.model;

import com.saasovation.identityaccess.domain.model.identity.TenantId;
import com.saasovation.identityaccess.domain.model.identity.User;

import java.util.Collection;

public interface UserRepository {
    User userFromAuthenticCredentials(TenantId aTenantId, String aUsername, String encryptedPassword);

    void add(User user);

    User userWithUsername(TenantId tenantId, String aUsername);

    void remove(User aUser);

    void removeAll(Collection<User> aUserCollection);
}
