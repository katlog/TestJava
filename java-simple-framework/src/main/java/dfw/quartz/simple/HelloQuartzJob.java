package dfw.quartz.simple;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @moudle: HelloQuartzJob 
 * @version:v1.0
 * @date: 2017年3月21日 下午3:38:44
 *
 */
public class HelloQuartzJob implements Job{

    /**
     * <p>Title: execute</p>
     * <p>author : fw</p>
     * <p>date : 2017年3月21日 下午3:39:08</p>
     * @param arg0
     * @throws JobExecutionException
     */ 
    @Override
    public void execute(JobExecutionContext context)
        throws JobExecutionException {
        //通过上下文来获取触发器名
        System.out.println("Hello, Quartz! - executing at "+new Date()+" by "+context.getTrigger().getCalendarName());     
        context.getTrigger().getCalendarName();
    }

}
