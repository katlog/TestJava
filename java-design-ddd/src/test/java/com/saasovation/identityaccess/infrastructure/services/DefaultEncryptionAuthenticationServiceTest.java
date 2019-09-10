package com.saasovation.identityaccess.infrastructure.services;

import com.saasovation.identityaccess.domain.model.DomainRegistry;
import com.saasovation.identityaccess.domain.model.identity.TenantId;
import com.saasovation.identityaccess.domain.model.identity.UserDescriptor;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultEncryptionAuthenticationServiceTest {

    @Test
    public void authenticate() {
        TenantId aTenantId = new TenantId("");
        String aUsername = "";
        String aPassword = "";

        // inside an Application Service client with
        // only task coordination responsibility（应用服务只用于协调任务)
        UserDescriptor userDescriptor =
                DomainRegistry
                        .authenticationService()
                        .authenticate(aTenantId, aUsername, aPassword);
    }
}