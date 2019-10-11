package com.saasovation.identityaccess.application;

import com.saasovation.identityaccess.domain.model.DomainRegistry;
import com.saasovation.identityaccess.domain.model.identity.GroupMemberService;
import com.saasovation.identityaccess.domain.model.identity.Role;
import com.saasovation.identityaccess.domain.model.identity.TenantId;
import com.saasovation.identityaccess.domain.model.identity.User;
import com.saasovation.supply.ApplicationService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fw on 2019/9/10
 * 一个应用服务 (14) ，它位于六边形内部，并向外提供API
 * 作为领域模型的直接客户， AccessService负责管理用例和事务。该用例包括查找一个User是否存在，若存在，再判断该User是否扮演了某个指定的角色
 */
@ApplicationService
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
