package name.katlog.dddimpl.chapter01;

import name.katlog.dddimpl.Entity;

/**
 * Created by fw on 2019/2/11
 */
public class BacklogItem extends Entity {

    private SprintId sprintId;
    private BacklogItemStatusType status;
    //...

    public void setSprintId(SprintId sprintId) {
        this.sprintId = sprintId;
    }
    public void setStatus(BacklogItemStatusType status) {
        this.status = status;
    }
    //...

}
