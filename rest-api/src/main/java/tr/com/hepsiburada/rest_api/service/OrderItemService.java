package tr.com.hepsiburada.rest_api.service;

import tr.com.hepsiburada.model.document.OrderItem;
import tr.com.hepsiburada.model.dto.repository.BestSellerProductsDTO;

import java.util.List;

public interface OrderItemService extends CrudService<OrderItem, Integer> {
    List<BestSellerProductsDTO> personalizedBestSeller10Products(List<String> categoryIds);

    List<BestSellerProductsDTO> nonPersonalizedBestSeller10Products();
}
