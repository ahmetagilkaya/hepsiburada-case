package tr.com.hepsiburada.model.dto.rest.history;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.hepsiburada.core.enumeration.ResponseType;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductViewDTO {

    private String userId;
    private List<String> product;
    private ResponseType type;

}
