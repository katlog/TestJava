package name.katlog.dddimpl.chapter01;

/**
 * Created by fw on 2019/2/11
 */
public class Sample4 {

    public static void main(String[] args) {

        BacklogItem backlogItem = new BacklogItem();
        SprintId sprintId = new SprintId();

        // client commits the backlog item to a sprint
        // by setting its sprintId and status
        backlogItem.setSprintId(sprintId);
        backlogItem.setStatus(BacklogItemStatusType.COMMITTED);

    }
}
