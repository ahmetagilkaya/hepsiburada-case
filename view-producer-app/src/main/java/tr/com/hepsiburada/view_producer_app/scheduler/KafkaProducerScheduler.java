package tr.com.hepsiburada.view_producer_app.scheduler;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tr.com.hepsiburada.model.dto.kafka.ProductViewKafkaDTO;
import tr.com.hepsiburada.view_producer_app.service.KafkaProducerService;

import java.util.Queue;

@Component
@RequiredArgsConstructor
public class KafkaProducerScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerScheduler.class);

    private final Queue<ProductViewKafkaDTO> productViewKafkaDTOQueue;
    private final KafkaProducerService kafkaProducerService;

    @Scheduled(fixedDelay = 1000)
    public void sendDataToKafkaEverySecond() {
        if (CollectionUtils.isEmpty(productViewKafkaDTOQueue)) {
            LOGGER.info("There is no data left in the collection to send Kafka");
            return;
        }

        boolean isDataSendedToKafka = kafkaProducerService.sendProductViewDataToKafka(productViewKafkaDTOQueue.peek());

        if (isDataSendedToKafka) {
            ProductViewKafkaDTO deletedProductViewKafkaDTO = productViewKafkaDTOQueue.poll();
            LOGGER.info(String.format("Data sended to Kafka : %s", deletedProductViewKafkaDTO));
        }

    }

}
