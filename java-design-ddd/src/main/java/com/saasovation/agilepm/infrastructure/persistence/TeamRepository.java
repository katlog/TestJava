package com.saasovation.agilepm.infrastructure.persistence;

import com.saasovation.agilepm.domain.model.team.Team;
import com.saasovation.agilepm.domain.model.team.TeamId;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

/**
 * Created by fw on 2019/8/26
 */
public interface TeamRepository {
    Team teamOfId(TenantId tenantId, TeamId teamId);
}
