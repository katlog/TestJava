package name.katlog.dddimpl.chapter01;

/**
 * Created by fw on 2019/2/11
 */
public class Sample5 {
    public static void main(String[] args) {

        BacklogItem1 backlogItem = new BacklogItem1();
        Sprint sprint = new Sprint();

        // client commits the backlog item to a sprint
        // by using a domain-specific behavior
        backlogItem.commitTo(sprint);

    }
}
