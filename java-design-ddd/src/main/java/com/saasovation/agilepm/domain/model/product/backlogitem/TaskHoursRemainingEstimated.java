package com.saasovation.agilepm.domain.model.product.backlogitem;

import com.saasovation.agilepm.domain.model.product.DomainEvent;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

import java.util.Date;

/**
 * Created by fw on 2019/8/26
 */
public class TaskHoursRemainingEstimated implements DomainEvent {

    private Date occurredOn;
    private TenantId tenantId;
    private BacklogItemId backlogItemId;
    private TaskId taskId;
    private int hoursRemaining;

    @Override
    public Date occurredOn() {
        return null;
    }
}
