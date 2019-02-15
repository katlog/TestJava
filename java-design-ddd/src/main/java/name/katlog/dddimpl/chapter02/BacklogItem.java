package name.katlog.dddimpl.chapter02;

import name.katlog.dddimpl.chapter01.BacklogItemStatusType;
import name.katlog.dddimpl.Entity;
import name.katlog.dddimpl.chapter01.SprintId;

/**
 * Created by fw on 2019/2/11
 */
public class BacklogItem extends Entity {

    // ...
    private BacklogItemId backlogItemId;
    private BusinessPriority businessPriority;
    // ...

}

class BacklogItemId{}
class BusinessPriority{}