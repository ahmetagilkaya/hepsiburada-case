package tr.com.hepsiburada.model.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tr.com.hepsiburada.model.document.base.BaseDocument;

@Data
@EqualsAndHashCode(callSuper = true)
@Document("PRODUCTS")
public class Product extends BaseDocument {

    @Id
    private String productId;
    private String categoryId;

}
