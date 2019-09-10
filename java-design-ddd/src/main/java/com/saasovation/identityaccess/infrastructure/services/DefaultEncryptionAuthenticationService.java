package com.saasovation.identityaccess.infrastructure.services;


import com.saasovation.identityaccess.domain.model.DomainRegistry;
import com.saasovation.identityaccess.domain.model.identity.AuthenticationService;
import com.saasovation.identityaccess.domain.model.identity.Tenant;
import com.saasovation.identityaccess.domain.model.identity.TenantId;
import com.saasovation.identityaccess.domain.model.identity.User;
import com.saasovation.identityaccess.domain.model.identity.UserDescriptor;

/**
 * Created by fw on 2019/3/21
 */
public class DefaultEncryptionAuthenticationService  implements AuthenticationService {
    public DefaultEncryptionAuthenticationService() {
        super();
    }
    @Override
    public UserDescriptor authenticate(TenantId aTenantId,String aUsername,String aPassword) {
        if (aTenantId == null) {
            throw new IllegalArgumentException("TenantId must not be null.");
        }
        if (aUsername == null) {
            throw new IllegalArgumentException("Username must not be null.");
        }
        if (aPassword == null) {
            throw new IllegalArgumentException("Password must not be null.");
        }
        UserDescriptor userDescriptor = null;
        Tenant tenant =
                DomainRegistry
                        .tenantRepository()
                        .tenantOfId(aTenantId);
        if (tenant != null && tenant.isActive()) {
            String encryptedPassword =
                    DomainRegistry
                            .encryptionService()
                            .encryptedValue(aPassword);
            User user =
                    DomainRegistry
                            .userRepository()
                            .userFromAuthenticCredentials(aTenantId,aUsername,encryptedPassword);
            if (user != null && user.isEnabled()) {
                userDescriptor = user.userDescriptor();
            }
        }
        return userDescriptor;
    }
}
