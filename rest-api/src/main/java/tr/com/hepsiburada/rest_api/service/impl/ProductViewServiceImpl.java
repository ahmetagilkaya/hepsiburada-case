package tr.com.hepsiburada.rest_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.hepsiburada.model.document.ProductView;
import tr.com.hepsiburada.model.dto.repository.MostThreeCategoriesDTO;
import tr.com.hepsiburada.rest_api.repository.ProductViewRepository;
import tr.com.hepsiburada.rest_api.service.ProductViewService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductViewServiceImpl implements ProductViewService {

    private final ProductViewRepository productViewRepository;

    @Override
    @Transactional(readOnly = true)
    public ProductView findById(String documentId) {
        return productViewRepository
                .findById(documentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("There is no record with documentId = %s on ProductViews Collection.", documentId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductView> findAll() {
        return productViewRepository.findAll();
    }

    @Override
    @Transactional
    public ProductView save(ProductView document) {
        return productViewRepository.save(document);
    }

    @Override
    @Transactional
    public ProductView update(ProductView document) {
        return productViewRepository.save(document);
    }

    @Override
    @Transactional
    public void delete(ProductView document) {
        productViewRepository.delete(document);
    }

    @Override
    @Transactional
    public void deleteById(String documentId) {
        productViewRepository.deleteById(documentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductView> findTop10ByUserIdOrderByViewTimeDesc(String userId) {
        return productViewRepository.findTop10ByUserIdOrderByViewTimeDesc(userId);
    }

    @Override
    @Transactional
    public void deleteByUserIdAndProductId(String userId, String productId) {
        productViewRepository.deleteByUserIdAndProductId(userId, productId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MostThreeCategoriesDTO> mostThreeCategoriesInUserHistory(String userId) {
        return productViewRepository.mostThreeCategoriesInUserHistory(userId).getMappedResults();
    }

}
