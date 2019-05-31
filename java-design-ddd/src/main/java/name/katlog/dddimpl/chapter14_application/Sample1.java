package name.katlog.dddimpl.chapter14_application;

import java.util.Set;

/**
 * Created by fw on 2019/3/18
 */
public class Sample1 {

    public class BacklogItem /*...*/ {
        // ...
        public void provideBacklogItemInterest(
                BacklogItemInterest anInterest) {

            // anInterest.informTenantId(this.tenantId().id());
            // anInterest.informProductId(this.productId().id());
            // anInterest.informBacklogItemId(this.backlogItemId().id());
            // anInterest.informStory(this.story());
            // anInterest.informSummary(this.summary());
            // anInterest.informType(this.type().toString());
            // ...
        }
        public void provideTasksInterest(TasksInterest anInterest) {
            Set<Task> tasks = this.allTasks();
            anInterest.informTaskCount(tasks.size());
            for (Task task : tasks) {
                // ...
            }
        }
        // ...
        private Set<Task> allTasks() {
            return null;
        }
    }
}
