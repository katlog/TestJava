package com.saasovation.identityaccess.domain.model;

import com.saasovation.identityaccess.domain.model.identity.Tenant;
import com.saasovation.identityaccess.domain.model.identity.TenantId;

public interface TenantRepository {
    Tenant tenantOfId(TenantId aTenantId);

    TenantId nextIdentity();
}
