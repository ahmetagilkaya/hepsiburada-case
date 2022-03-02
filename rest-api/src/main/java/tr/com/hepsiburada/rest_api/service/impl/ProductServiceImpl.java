package tr.com.hepsiburada.rest_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tr.com.hepsiburada.model.document.Product;
import tr.com.hepsiburada.rest_api.repository.ProductRepository;
import tr.com.hepsiburada.rest_api.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product findById(String documentId) {
        return productRepository
                .findById(documentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("There is no record with documentId = %s on Products Collection.", documentId)));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product document) {
        return productRepository.save(document);
    }

    @Override
    public Product update(Product document) {
        return productRepository.save(document);
    }

    @Override
    public void delete(Product document) {
        productRepository.delete(document);
    }

    @Override
    public void deleteById(String documentId) {
        productRepository.deleteById(documentId);
    }

}
