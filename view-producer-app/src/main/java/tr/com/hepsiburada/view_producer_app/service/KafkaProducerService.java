package tr.com.hepsiburada.view_producer_app.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tr.com.hepsiburada.model.dto.kafka.ProductViewKafkaDTO;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    @Value("${kafka.hepsiburada.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, ProductViewKafkaDTO> producerViewKafkaTemplate;

    public boolean sendProductViewDataToKafka(ProductViewKafkaDTO productViewKafkaDTO) {
        productViewKafkaDTO.setTimestamp(System.currentTimeMillis());
        producerViewKafkaTemplate.send(new ProducerRecord<>(topicName, productViewKafkaDTO));
        return true;
    }

}
