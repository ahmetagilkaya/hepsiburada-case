package tr.com.hepsiburada.rest_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;
import tr.com.hepsiburada.model.document.OrderItem;
import tr.com.hepsiburada.model.dto.repository.BestSellerProductsDTO;
import tr.com.hepsiburada.rest_api.repository.OrderItemRepository;
import tr.com.hepsiburada.rest_api.service.OrderItemService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional(readOnly = true)
    public OrderItem findById(Integer documentId) {
        return orderItemRepository
                .findById(documentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("There is no record with documentId = %s on OrderItems Collection.", documentId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    @Transactional
    public OrderItem save(OrderItem document) {
        return orderItemRepository.save(document);
    }

    @Override
    @Transactional
    public OrderItem update(OrderItem document) {
        return orderItemRepository.save(document);
    }

    @Override
    @Transactional
    public void delete(OrderItem document) {
        orderItemRepository.delete(document);
    }

    @Override
    @Transactional
    public void deleteById(Integer documentId) {
        orderItemRepository.deleteById(documentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BestSellerProductsDTO> personalizedBestSeller10Products(List<String> categoryIds) {
        if (CollectionUtils.isEmpty(categoryIds)) {
            return new ArrayList<>();
        }
        if (categoryIds.size() == 1) {
            return orderItemRepository.personalizedBestSeller10Products(categoryIds.get(0)).getMappedResults();
        }
        if (categoryIds.size() == 2) {
            return orderItemRepository.personalizedBestSeller10Products(categoryIds.get(0), categoryIds.get(1)).getMappedResults();
        }
        return orderItemRepository.personalizedBestSeller10Products(categoryIds.get(0), categoryIds.get(1), categoryIds.get(2)).getMappedResults();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BestSellerProductsDTO> nonPersonalizedBestSeller10Products() {
        return orderItemRepository.nonPersonalizedBestSeller10Products().getMappedResults();
    }

}
