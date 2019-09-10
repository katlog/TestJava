package com.saasovation.identityaccess.infrastructure.services;

import com.saasovation.identityaccess.domain.model.DomainRegistry;
import com.saasovation.identityaccess.domain.model.identity.TenantId;
import com.saasovation.identityaccess.domain.model.identity.User;
import com.saasovation.identityaccess.domain.model.identity.UserDescriptor;

import static org.junit.Assert.*;

/**
 * Created by fw on 2019/9/9
 */
public class AuthenticationServiceTest {

    private static final String FIXTURE_PASSWORD = "123123";

    public void testAuthenticationSuccess() throws Exception {
        User user = this.getUserFixture();
        DomainRegistry
                .userRepository()
                .add(user);
        UserDescriptor userDescriptor =
                DomainRegistry
                        .authenticationService()
                        .authenticate(
                                user.tenantId(),
                                user.username(),
                                FIXTURE_PASSWORD);

        assertNotNull(userDescriptor);
        assertEquals(user.tenantId(), userDescriptor.tenantId());
        assertEquals(user.username(), userDescriptor.username());
        assertEquals(user.person().emailAddress(), userDescriptor.emailAddress());
    }

    private User getUserFixture() {
        return null;
    }

    public void testAuthenticationTenantFailure() throws Exception {
        User user = this.getUserFixture();
        DomainRegistry
                .userRepository()
                .add(user);
        TenantId bogusTenantId = DomainRegistry.tenantRepository().nextIdentity();
        UserDescriptor userDescriptor =
                DomainRegistry
                        .authenticationService()
                        .authenticate(
                                bogusTenantId, // bogus（假的）
                                user.username(),
                                FIXTURE_PASSWORD);
        assertNull(userDescriptor);
    }

    public void testAuthenticationUsernameFailure() throws Exception {
        User user = this.getUserFixture();
        DomainRegistry
                .userRepository()
                .add(user);
        UserDescriptor userDescriptor =
                DomainRegistry
                        .authenticationService()
                        .authenticate(
                                user.tenantId(),
                                "bogususername",
                                user.password());
        assertNull(userDescriptor);
    }

    public void testAuthenticationPasswordFailure() throws Exception {
        User user = this.getUserFixture();
        DomainRegistry
                .userRepository()
                .add(user);
        UserDescriptor userDescriptor =
                DomainRegistry
                        .authenticationService()
                        .authenticate(
                                user.tenantId(),
                                user.username(),
                                "passw0rd");
        assertNull(userDescriptor);
    }




}
