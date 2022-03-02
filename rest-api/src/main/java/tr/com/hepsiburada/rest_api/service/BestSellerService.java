package tr.com.hepsiburada.rest_api.service;

import tr.com.hepsiburada.model.dto.rest.history.ProductViewDTO;

public interface BestSellerService extends BaseService {

    ProductViewDTO getBestSellerProductsByUser(String userId);

}
