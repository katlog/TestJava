package name.katlog.dddimpl.chapter01;

import name.katlog.dddimpl.assist.DomainEvent;

/**
 * Created by fw on 2019/2/11
 */
public class BacklogItemCommitted extends DomainEvent {
    public BacklogItemCommitted(Object tenant, Object backlogItemId, SprintId sprintId) {
        super();
    }
}
