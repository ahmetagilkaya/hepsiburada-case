package tr.com.hepsiburada.rest_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.hepsiburada.core.enumeration.ResponseType;
import tr.com.hepsiburada.model.document.ProductView;
import tr.com.hepsiburada.model.dto.rest.history.ProductViewDTO;
import tr.com.hepsiburada.rest_api.core.helper.EndpointResponseHelper;
import tr.com.hepsiburada.rest_api.service.HistoryService;
import tr.com.hepsiburada.rest_api.service.ProductViewService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final ProductViewService productViewService;
    private final EndpointResponseHelper endpointResponseHelper;

    @Override
    @Transactional(readOnly = true)
    public ProductViewDTO getLastTenProductsViewedByUser(String userId) {
        List<ProductView> lastTenProductsViewedByUserResultList = productViewService.findTop10ByUserIdOrderByViewTimeDesc(userId);
        List<String> products = lastTenProductsViewedByUserResultList.stream().map(ProductView::getProductId).collect(Collectors.toList());

        return endpointResponseHelper.checkResponse(userId, products, ResponseType.PERSONALIZED);
    }

    @Override
    @Transactional
    public void deleteProductFromUserHistory(String userId, String productId) {
        productViewService.deleteByUserIdAndProductId(userId, productId);
    }

}
