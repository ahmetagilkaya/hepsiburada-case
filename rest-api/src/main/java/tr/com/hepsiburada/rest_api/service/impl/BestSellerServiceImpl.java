package tr.com.hepsiburada.rest_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tr.com.hepsiburada.core.enumeration.ResponseType;
import tr.com.hepsiburada.model.dto.repository.BestSellerProductsDTO;
import tr.com.hepsiburada.model.dto.repository.MostThreeCategoriesDTO;
import tr.com.hepsiburada.model.dto.rest.history.ProductViewDTO;
import tr.com.hepsiburada.rest_api.core.helper.EndpointResponseHelper;
import tr.com.hepsiburada.rest_api.service.BestSellerService;
import tr.com.hepsiburada.rest_api.service.OrderItemService;
import tr.com.hepsiburada.rest_api.service.ProductViewService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BestSellerServiceImpl implements BestSellerService {

    private final ProductViewService productViewService;
    private final OrderItemService orderItemService;
    private final EndpointResponseHelper endpointResponseHelper;

    @Override
    @Transactional(readOnly = true)
    public ProductViewDTO getBestSellerProductsByUser(String userId) {
        List<MostThreeCategoriesDTO> mostThreeCategoriesDTOList = productViewService.mostThreeCategoriesInUserHistory(userId);
        List<String> mostThreeCategoryIds = mostThreeCategoriesDTOList.stream().map((mostThreeCategoriesDTO -> mostThreeCategoriesDTO.get_id().get(0))).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(mostThreeCategoryIds)) {
            List<String> products = orderItemService.nonPersonalizedBestSeller10Products().stream().map((BestSellerProductsDTO::get_id)).collect(Collectors.toList());
            return endpointResponseHelper.checkResponse(userId, products, ResponseType.NON_PERSONALIZED);
        }
        List<String> products = orderItemService.personalizedBestSeller10Products(mostThreeCategoryIds).stream().map((BestSellerProductsDTO::get_id)).collect(Collectors.toList());

        return endpointResponseHelper.checkResponse(userId, products, ResponseType.PERSONALIZED);
    }

}
