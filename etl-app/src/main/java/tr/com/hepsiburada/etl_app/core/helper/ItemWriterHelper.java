package tr.com.hepsiburada.etl_app.core.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ItemWriterHelper {

    private final MongoTemplate mongoTemplate;

    public <O> MongoItemWriter<O> createMongoItemWriter(String collectionName) {
        return new MongoItemWriterBuilder<O>()
                .template(mongoTemplate)
                .collection(collectionName)
                .build();
    }

    public <T> void writeAndClearList(ItemWriter<T> itemWriter, ArrayList<T> readedList) {
        try {
            itemWriter.write(readedList);
            readedList.clear();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
