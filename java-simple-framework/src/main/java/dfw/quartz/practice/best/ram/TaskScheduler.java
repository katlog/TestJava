package dfw.quartz.practice.best.ram;

import lombok.extern.log4j.Log4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

@Log4j
public final class TaskScheduler {

    static {
        try {
            //获取调度器实例
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();

            //启动调度器，停止使用shutdown(true);
            new SpecificTask(scheduler, "group", "0/30 * * * * ?").register();

            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void start(){
    }

    public static void main(String[] args) {
        System.out.println("args = " + args);
    }
}
