package tr.com.hepsiburada.etl_app.core.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class StepBuilderHelper {

    private final StepBuilderFactory stepBuilderFactory;

    public <I, O> Step createStep(String stepName, ItemReader<I> reader, ItemWriter<O> writer) {
        return stepBuilderFactory
                .get(stepName)
                .<I, O>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

}
