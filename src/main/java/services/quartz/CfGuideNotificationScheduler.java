package services.quartz;

import static org.quartz.JobBuilder.newJob;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CfGuideNotificationScheduler {

  public static void main(String[] args) {

    Scheduler scheduler = null;

    try {
      scheduler = StdSchedulerFactory.getDefaultScheduler();

      scheduler.start();

      JobDetail jobDetail = newJob(CfGuideNotificationJob.class)
          .withIdentity("CfGuideNotificationJob")
          .build();

      Trigger trigger = TriggerBuilder.newTrigger()
          .withIdentity("WeeklyTrigger")
          .startNow()
          .withSchedule(SimpleScheduleBuilder
              .simpleSchedule()
              .withIntervalInHours(168)
              .repeatForever())
          .build();

      scheduler.scheduleJob(jobDetail, trigger);

    } catch (SchedulerException e) {
      e.printStackTrace();
    }

  }

}
