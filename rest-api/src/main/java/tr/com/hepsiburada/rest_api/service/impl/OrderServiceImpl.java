package tr.com.hepsiburada.rest_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.hepsiburada.model.document.Order;
import tr.com.hepsiburada.rest_api.repository.OrderRepository;
import tr.com.hepsiburada.rest_api.service.OrderService;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    @Transactional(readOnly = true)
    public Order findById(BigDecimal documentId) {
        return orderRepository
                .findById(documentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("There is no record with documentId = %s on Orders Collection.", documentId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order save(Order document) {
        return orderRepository.save(document);
    }

    @Override
    @Transactional
    public Order update(Order document) {
        return orderRepository.save(document);
    }

    @Override
    @Transactional
    public void delete(Order document) {
        orderRepository.delete(document);
    }

    @Override
    @Transactional
    public void deleteById(BigDecimal documentId) {
        orderRepository.deleteById(documentId);
    }
    
}
