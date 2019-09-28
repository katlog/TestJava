package com.saasovation.identityaccess.domain.model;

import com.saasovation.identityaccess.domain.model.identity.Role;
import com.saasovation.identityaccess.domain.model.identity.TenantId;

public interface RoleRepository {
    Role roleNamed(TenantId tenantId, String aRoleName);
}
