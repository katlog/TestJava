package com.saasovation.identityaccess.application;

import com.saasovation.identityaccess.domain.model.DomainRegistry;
import com.saasovation.identityaccess.domain.model.identity.Role;
import com.saasovation.identityaccess.domain.model.identity.TenantId;
import com.saasovation.identityaccess.domain.model.identity.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fw on 2019/9/10
 */
public class AccessService {
    //...

    /** chapter 13 集成限界上下文 rest方式  */
    @Transactional(readOnly = true)
    public User userInRole(
            String aTenantId,
            String aUsername,
            String aRoleName) {

        User userInRole = null;
        TenantId tenantId = new TenantId(aTenantId);
        User user =
                DomainRegistry
                        .userRepository()
                        .userWithUsername(tenantId, aUsername);
        if (user != null) {
            Role role =
                    DomainRegistry
                            .roleRepository()
                            .roleNamed(tenantId, aRoleName);
            if (role != null) {
                GroupMemberService groupMemberService =
                        DomainRegistry.groupMemberService();
                if (role.isInRole(user, groupMemberService)) {
                    userInRole = user;
                }
            }
        }
        return userInRole;
    }
    // ...
}
