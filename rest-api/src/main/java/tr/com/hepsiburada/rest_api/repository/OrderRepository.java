package tr.com.hepsiburada.rest_api.repository;

import org.springframework.stereotype.Repository;
import tr.com.hepsiburada.model.document.Order;
import tr.com.hepsiburada.rest_api.repository.base.BaseRepository;

import java.math.BigDecimal;

@Repository
public interface OrderRepository extends BaseRepository<Order, BigDecimal> {
}
