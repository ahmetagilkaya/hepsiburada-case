package tr.com.hepsiburada.model.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tr.com.hepsiburada.model.document.base.BaseDocument;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Document("ORDERS")
public class Order extends BaseDocument {

    @Id
    private BigDecimal orderId;
    private String userId;

}
