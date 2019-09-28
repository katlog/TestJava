package com.saasovation.identityaccess.domain.model.identity;

import com.saasovation.identityaccess.domain.model.DomainRegistry;
import com.saasovation.supply.DomianService;

/** 该接口和那些与身份相关的聚合（比如Tenant，User和Group)定义在相同的模块（9）中，
 * 因AuthenticationService也是一个与身份相关的概念
 *
 * 因为知道不会再有另外的实现类，所以启用独立接口
 * */
@DomianService
public /*interface */class AuthenticationService {

    public UserDescriptor authenticate(TenantId aTenantId, String aUsername, String aPassword){
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
    };

}
