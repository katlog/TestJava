package name.katlog.quartz.practice.best.ram;

import org.quartz.*;

abstract class AbstractCronJob implements Job {

    private static final String JOB_NAME_SUFFIX = "_job";
    private static final String TRIGGER_NAME_SUFFIX = "_trigger";

    private Scheduler scheduler;
    private String groupName;
    private String cron;

    private Class<? extends AbstractCronJob> cronJobClass = getClass();
    protected String cronJobFullQualifiedName = cronJobClass.getName();

    @Deprecated // quartz需要默认构造器
    AbstractCronJob(){
    }

    AbstractCronJob(Scheduler scheduler, String groupName, String cron){
        this.scheduler = scheduler;
        this.groupName = groupName;
        this.cron = cron;
    }

    AbstractCronJob register() throws SchedulerException {

        String jobName = cronJobFullQualifiedName + JOB_NAME_SUFFIX;
        JobDetail jobDetail = JobBuilder.newJob(cronJobClass)
                .withIdentity(jobName, groupName)
                .build();

        String triggerName = cronJobFullQualifiedName + TRIGGER_NAME_SUFFIX;
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().
                withIdentity(triggerName, groupName).startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(cron)
                ).build();

        scheduler.scheduleJob(jobDetail, cronTrigger);
        return this;
    }


}
