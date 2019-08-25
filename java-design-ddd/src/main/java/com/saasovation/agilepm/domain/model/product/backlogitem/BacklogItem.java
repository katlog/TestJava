package com.saasovation.agilepm.domain.model.product.backlogitem;

import com.saasovation.agilepm.domain.model.DomainEventPublisher;
import com.saasovation.agilepm.domain.model.product.sprint.Sprint;
import com.saasovation.agilepm.domain.model.product.sprint.SprintId;
import com.saasovation.agilepm.domain.model.tenant.Tenant;
import com.saasovation.supply.AggregateRoot;

/**
 * Created by fw on 2019/3/20
 */
@AggregateRoot
public class BacklogItem {

    private BacklogItemId backlogItemId;
    private BusinessPriority businessPriority;

    /**  chapter 1 提交到待定项 */
    private Tenant tenant;

    /**  ****************************** */
    private SprintId sprintId;
    private BacklogItemStatusType status;

    public void commitTo(Sprint aSprint) {
        if (!this.isScheduledForRelease()) {
            throw new IllegalStateException(
                    "Must be scheduled for release to commit to sprint.");
        }
        if (this.isCommittedToSprint()) {
            if (!aSprint.sprintId().equals(this.sprintId())) {
                this.uncommitFromSprint();
            }
        }
        this.elevateStatusWith(BacklogItemStatusType.COMMITTED);
        this.setSprintId(aSprint.sprintId());
        DomainEventPublisher.instance()
                .publish(new BacklogItemCommitted(this.tenant(), this.backlogItemId(), this.sprintId()));
    }


    /**  ****************************** */

    private BacklogItemId backlogItemId() {
        return this.backlogItemId;
    }

    private Tenant tenant() {
        return this.tenant;
    }
    private void setSprintId(SprintId sprintId) {
        this.sprintId = sprintId;
    }

    private void elevateStatusWith(BacklogItemStatusType backlogItemStatus) {

    }


    private void uncommitFromSprint() {

    }

    private SprintId sprintId() {
        return sprintId;
    }

    private boolean isCommittedToSprint() {
        return false;
    }

    private boolean isScheduledForRelease() {
        return false;
    }
    /**  chapter 1 提交到待定项 */
}


