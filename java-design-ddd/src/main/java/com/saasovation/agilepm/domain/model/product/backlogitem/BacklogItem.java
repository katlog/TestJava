package com.saasovation.agilepm.domain.model.product.backlogitem;

import com.saasovation.agilepm.domain.model.DomainEventPublisher;
import com.saasovation.agilepm.domain.model.product.ProductId;
import com.saasovation.agilepm.domain.model.product.StoryPoints;
import com.saasovation.agilepm.domain.model.product.sprint.Sprint;
import com.saasovation.agilepm.domain.model.product.sprint.SprintId;
import com.saasovation.agilepm.domain.model.team.Team;
import com.saasovation.agilepm.domain.model.team.TeamId;
import com.saasovation.agilepm.domain.model.team.TeamMemberId;
import com.saasovation.agilepm.domain.model.tenant.Tenant;
import com.saasovation.agilepm.domain.model.tenant.TenantId;
import com.saasovation.supply.AggregateRoot;

/**
 * Created by fw on 2019/3/20
 */
@AggregateRoot
public class BacklogItem {

    private BacklogItemId backlogItemId;
    private BacklogItemType type;

    private BusinessPriority businessPriority;


    private String story;

    private ProductId productId;
    private StoryPoints storyPoints;
    private Tenant tenant;
    private TeamId teamId;

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
        // 边界外的最终一致性 通过领域事件
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


    public TeamId teamId(){
        return null;
    }

    public TenantId tenantId(){
        return null;
    }

    public void assignTeamMemberToTask(TeamMemberId teamMemberId, Team ofTeam, TaskId taskId) {

    }

    public boolean hasBusinessPriority() {
        return false;
    }

    public BusinessPriority businessPriority() {
        return businessPriority;
    }
}


