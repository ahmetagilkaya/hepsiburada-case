package tr.com.hepsiburada.rest_api.service;

import tr.com.hepsiburada.model.document.ProductView;
import tr.com.hepsiburada.model.dto.repository.MostThreeCategoriesDTO;

import java.util.List;

public interface ProductViewService extends CrudService<ProductView, String> {
    List<ProductView> findTop10ByUserIdOrderByViewTimeDesc(String userId);

    void deleteByUserIdAndProductId(String userId, String productId);

    List<MostThreeCategoriesDTO> mostThreeCategoriesInUserHistory(String userId);
}
