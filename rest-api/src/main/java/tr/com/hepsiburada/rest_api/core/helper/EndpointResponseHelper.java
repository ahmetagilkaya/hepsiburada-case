package tr.com.hepsiburada.rest_api.core.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tr.com.hepsiburada.core.enumeration.ResponseType;
import tr.com.hepsiburada.model.dto.rest.history.ProductViewDTO;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EndpointResponseHelper {

    public ProductViewDTO checkResponse(String userId, List<String> products, ResponseType type) {
        if (products.size() < 5) {
            return new ProductViewDTO(userId, new ArrayList<>(), type);
        }
        return new ProductViewDTO(userId, products, type);
    }

}
