package tr.com.hepsiburada.rest_api.service;

import tr.com.hepsiburada.model.dto.rest.history.ProductViewDTO;

public interface HistoryService extends BaseService {

    ProductViewDTO getLastTenProductsViewedByUser(String userId);

    void deleteProductFromUserHistory(String userId, String productId);
    
}
