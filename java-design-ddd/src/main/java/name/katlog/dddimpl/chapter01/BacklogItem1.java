package name.katlog.dddimpl.chapter01;

import name.katlog.dddimpl.assist.DomainEventPublisher;

/**
 * Created by fw on 2019/2/11
 */
public class BacklogItem1 extends Entity {
    private SprintId sprintId;
    private BacklogItemStatusType status;
    //...

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

    private Object backlogItemId() {
        return null;
    }

    private Object tenant() {
        return null;
    }

    private void setSprintId(SprintId sprintId) {

    }

    private void elevateStatusWith(BacklogItemStatusType committed) {

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
    //...
}

