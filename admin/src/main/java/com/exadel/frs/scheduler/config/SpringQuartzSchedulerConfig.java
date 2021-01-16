package com.exadel.frs.scheduler.config;

import com.exadel.frs.scheduler.job.StatisticsJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SpringQuartzSchedulerConfig {

//    @Autowired
//    ApplicationContext applicationContext;

    @Bean
    JobDetail jobDetail() {
        return JobBuilder.newJob(StatisticsJob.class)
                .withIdentity("YourJob")
                .storeDurably()
                .build();
    }

    @Bean
    Trigger trigger() {
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder
                .newTrigger()
                .forJob(jobDetail())
                .withIdentity("Trigger");
        triggerBuilder
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(60)
                        .repeatForever())
                .startNow();
        return triggerBuilder.build();
    }

    @Bean
    public Scheduler scheduler(Trigger trigger, JobDetail job, SchedulerFactoryBean factory) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        //scheduler.setJobFactory(springBeanJobFactory());
        //scheduler.scheduleJob(job, trigger);
        scheduler.start();
        return scheduler;
    }
//
//    @Bean
//    public SpringBeanJobFactory springBeanJobFactory() {
//        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
//        jobFactory.setApplicationContext(applicationContext);
//        return jobFactory;
//    }
}
