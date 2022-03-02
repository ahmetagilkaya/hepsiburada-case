package tr.com.hepsiburada.model.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductViewKafkaDTO {
    private String event;
    private String messageid;
    private String userid;
    private Long timestamp;
    private PropertiesKafkaDTO properties;
    private ContextKafkaDTO context;
}
