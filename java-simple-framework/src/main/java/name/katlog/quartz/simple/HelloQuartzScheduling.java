package name.katlog.quartz.simple;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class HelloQuartzScheduling {

    public static void main(String[] args) throws SchedulerException {
            
        //获取调度器实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        //通过JobDetail封装创建的job任务类，同时指定job在scheduler中的组和名字
        JobDetail jobDetail = JobBuilder.newJob(HelloQuartzJob.class).withIdentity("job_1", "group_1").build();
        
        //创建触发器，指定所属组和名字
        //设置调度器的开始时间，运行间隔，运行次数
        Trigger trigger = TriggerBuilder.newTrigger().
            withIdentity("trigger_1", "group_1").startNow()
//            .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(10) //时间间隔
//                .withRepeatCount(5)        //重复次数(将执行6次)
            .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?") //每2秒执行一次
                )
        .build();

        //注册并进行调度
        scheduler.scheduleJob(jobDetail,trigger);
        
        //启动调度器，停止使用shutdown(true);
        scheduler.start();
    }
}
