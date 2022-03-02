package tr.com.hepsiburada.stream_reader_app.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tr.com.hepsiburada.model.dto.kafka.ProductViewKafkaDTO;
import tr.com.hepsiburada.stream_reader_app.core.helper.ConvertDtoToDocumentHelper;
import tr.com.hepsiburada.stream_reader_app.repository.ProductViewRepository;
import tr.com.hepsiburada.stream_reader_app.service.KafkaToDatabaseService;

@Service
@RequiredArgsConstructor
public class KafkaToMongodbService implements KafkaToDatabaseService<ProductViewKafkaDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaToMongodbService.class);

    private final ProductViewRepository productViewRepository;
    private final ConvertDtoToDocumentHelper convertDtoToDocumentHelper;

    @Override
    public void convertAndSave(ProductViewKafkaDTO inputData) {
        tr.com.hepsiburada.model.document.ProductView productView = convertDtoToDocumentHelper.convertProductViewToMap(inputData);
        productViewRepository.save(productView);
        LOGGER.info(String.format("Data has been saved in the database : %s", productView));
    }

}
