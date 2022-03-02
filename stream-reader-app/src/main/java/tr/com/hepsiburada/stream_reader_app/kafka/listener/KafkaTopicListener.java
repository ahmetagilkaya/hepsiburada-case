package tr.com.hepsiburada.stream_reader_app.kafka.listener;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import tr.com.hepsiburada.model.dto.kafka.ProductViewKafkaDTO;
import tr.com.hepsiburada.stream_reader_app.service.KafkaToDatabaseService;

@Component
@RequiredArgsConstructor
public class KafkaTopicListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTopicListener.class);

    private final KafkaToDatabaseService kafkaToMongodbService;

    @KafkaListener(id = "${kafka.hepsiburada.topic.name}", topics = "${kafka.hepsiburada.topic.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "productViewKafkaListenerContainerFactory")
    public void consumeProductViewData(@Payload ProductViewKafkaDTO productViewKafkaDTO, @Headers MessageHeaders headers) {
        LOGGER.info(String.format("Coming Data From Kafka : %s", productViewKafkaDTO));
        kafkaToMongodbService.convertAndSave(productViewKafkaDTO);
    }

}
