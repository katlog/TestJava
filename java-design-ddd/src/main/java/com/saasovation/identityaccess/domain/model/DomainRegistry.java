package com.saasovation.identityaccess.domain.model;

import com.saasovation.identityaccess.domain.model.identity.AuthenticationService;

/**
 * Created by fw on 2019/3/21
 */
public class DomainRegistry {
    public static TenantRepository tenantRepository() {
        return null;
    }

    public static UserRepository userRepository() {
        return null;
    }

    public static EncryptionService encryptionService() {
        return null;
    }

    public static AuthenticationService authenticationService() {
        return null;
    }
}
