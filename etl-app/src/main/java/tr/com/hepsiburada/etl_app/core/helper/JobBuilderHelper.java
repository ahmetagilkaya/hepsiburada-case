package tr.com.hepsiburada.etl_app.core.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class JobBuilderHelper {

    private final JobBuilderFactory jobBuilderFactory;

    public Job createJob(String jobName, Step step) {
        return jobBuilderFactory
                .get(jobName)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

}
