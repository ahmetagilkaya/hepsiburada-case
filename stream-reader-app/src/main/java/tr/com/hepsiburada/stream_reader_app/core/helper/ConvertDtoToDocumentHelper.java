package tr.com.hepsiburada.stream_reader_app.core.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.hepsiburada.model.document.ProductView;
import tr.com.hepsiburada.model.dto.kafka.ProductViewKafkaDTO;

@Component
@RequiredArgsConstructor
public class ConvertDtoToDocumentHelper {

    public ProductView convertProductViewToMap(ProductViewKafkaDTO productViewKafkaDTO) {
        ProductView productViewDocument = new ProductView();
        productViewDocument.setId(productViewKafkaDTO.getMessageid());
        productViewDocument.setUserId(productViewKafkaDTO.getUserid());
        productViewDocument.setProductId(productViewKafkaDTO.getProperties().getProductid());
        productViewDocument.setSource(productViewKafkaDTO.getContext().getSource());
        productViewDocument.setViewTime(productViewKafkaDTO.getTimestamp());
        return productViewDocument;
    }

}
