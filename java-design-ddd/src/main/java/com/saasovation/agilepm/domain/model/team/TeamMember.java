package com.saasovation.agilepm.domain.model.team;

import com.saasovation.agilepm.domain.model.tenant.TenantId;
import com.saasovation.supply.AggregateRoot;

/**
 * Created by fw on 2019/3/20
 */
@AggregateRoot
public class TeamMember {
    private TenantId tenantId;
}
