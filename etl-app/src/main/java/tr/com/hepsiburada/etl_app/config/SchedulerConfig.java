package tr.com.hepsiburada.etl_app.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
@EnableBatchProcessing
@RequiredArgsConstructor
public class SchedulerConfig implements SchedulingConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerConfig.class);

    @Value("${app.task.pool.corePoolSize}")
    private Integer corePoolSize;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();

        scheduler.setPoolSize(corePoolSize);
        scheduler.setThreadNamePrefix("Scheduler_");
        scheduler.initialize();

        scheduledTaskRegistrar.setTaskScheduler(scheduler);
    }

}
