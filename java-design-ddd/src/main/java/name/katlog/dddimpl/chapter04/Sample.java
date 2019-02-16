package name.katlog.dddimpl.chapter04;

import name.katlog.dddimpl.assist.Transactional;
import name.katlog.dddimpl.chapter01.Sprint;

/**
 * Created by fw on 2019/2/14
 */
public class Sample {

    private BacklogItemRepository backlogItemRepository;
    private SprintRepository sprintRepository;

    @Transactional(rollbackFor = Exception.class)
    public void commitBacklogItemToSprint(String aTenantId, String aBacklogItemId, String aSprintId) {

        TenantId tenantId = new TenantId(aTenantId);
        BacklogItem backlogItem =
                backlogItemRepository.backlogItemOfId(tenantId, new BacklogItemId(aBacklogItemId));
        Sprint sprint = sprintRepository.sprintOfId(tenantId, new SprintId(aSprintId));
        backlogItem.commitTo(sprint);
    }
}
