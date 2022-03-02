package tr.com.hepsiburada.model.dto.etl;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderEtlDTO {

    private BigDecimal order_id;
    private String user_id;

}
