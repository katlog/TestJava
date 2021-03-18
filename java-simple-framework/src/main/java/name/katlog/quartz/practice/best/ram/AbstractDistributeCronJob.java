package name.katlog.quartz.practice.best.ram;

import lombok.extern.log4j.Log4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;

@Log4j
public abstract class AbstractDistributeCronJob extends AbstractCronJob{

    @Deprecated
    protected AbstractDistributeCronJob(){}
    protected AbstractDistributeCronJob(Scheduler scheduler, String groupName, String cron) {
        super(scheduler, groupName, cron);
    }

    String getLockKey(){
        return super.cronJobFullQualifiedName;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("jobExecutionContext = " + jobExecutionContext);
        // Jedis jedis = RedisUtil.getJedis();
        try {
            if (/*RedisDisLock.acquireLock(jedis, getLockKey(), 10)*/true) {
                // log.info("----------start----------" + super.cronJobFullQualifiedName + "---------");
                jobRun();
                // log.info("----------end----------" + super.cronJobFullQualifiedName + "---------");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // log.error("execute error:",e);
        } finally {
            // RedisDisLock.unLock(jedis, getLockKey());
        }

    }

    protected abstract void jobRun();
}
