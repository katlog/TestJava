package com.saasovation.agilepm.domain.model.team;

import com.saasovation.agilepm.domain.model.tenant.TenantId;
import com.saasovation.supply.AggregateRoot;

import java.util.Set;

/**
 * Created by fw on 2019/3/20
 */
@AggregateRoot
public class Team {
    private TenantId tenantId;

    private ProductOwner productOwner;

    private Set<TeamMember> teamMembers;
}
