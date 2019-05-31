package com.saasovation.agilepm.domain.model.product;

import lombok.AccessLevel;
import lombok.Setter;

import java.util.Date;

/**
 * Created by fw on 2019/3/18
 */
public class BacklogItemCommitted implements DomainEvent {

    @Setter(AccessLevel.PRIVATE)
    private Date occurredOn;

    // TODO: 2019/3/18 创建  BacklogItemId SprintId TenantId

    // private BacklogItemId backlogItemId;
    // private SprintId committedToSprintId;
    // private TenantId tenantId;
    //...

    // public BacklogItemCommitted(TenantId aTenantId,BacklogItemId aBacklogItemId,SprintId aCommittedToSprintId) {
    //     super();
    //     this.setOccurredOn(new Date());
    //     this.setBacklogItemId(aBacklogItemId);
    //     this.setCommittedToSprintId(aCommittedToSprintId);
    //     this.setTenantId(aTenantId);
    // }

    @Override
    public Date occurredOn() {
        return this.occurredOn;
    }

    // public BacklogItemId backlogItemId() {
    //     return this.backlogItemId;
    // }
    // public SprintId committedToSprintId() {
    //     return this.committedToSprintId;
    // }
    // public TenantId tenantId() {
    //     return this.tenant;
    // }
}
