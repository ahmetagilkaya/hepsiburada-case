package tr.com.hepsiburada.model.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tr.com.hepsiburada.model.document.base.BaseDocument;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Document("ORDER_ITEMS")
public class OrderItem extends BaseDocument {

    @Id
    private Integer id;
    private String productId;
    private BigDecimal orderId;
    private BigDecimal quantity;
    private String categoryId;
    private String userId;

}
