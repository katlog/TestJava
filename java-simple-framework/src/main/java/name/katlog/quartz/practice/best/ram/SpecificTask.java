package name.katlog.quartz.practice.best.ram;

import lombok.extern.log4j.Log4j;
import org.quartz.Scheduler;

@Log4j
public class SpecificTask extends AbstractDistributeCronJob {


    public SpecificTask(Scheduler scheduler, String groupName, String cron){
        super(scheduler, groupName, cron);
    }

    @Override
    public void jobRun() {
        // todo 具体执行
    }

}
