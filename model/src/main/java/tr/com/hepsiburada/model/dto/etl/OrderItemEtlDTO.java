package tr.com.hepsiburada.model.dto.etl;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemEtlDTO {

    private Integer id;
    private String product_id;
    private BigDecimal order_id;
    private BigDecimal quantity;

}
