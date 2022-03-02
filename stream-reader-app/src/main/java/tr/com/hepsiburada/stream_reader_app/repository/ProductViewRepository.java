package tr.com.hepsiburada.stream_reader_app.repository;

import org.springframework.stereotype.Repository;
import tr.com.hepsiburada.model.document.ProductView;
import tr.com.hepsiburada.stream_reader_app.repository.base.BaseRepository;

@Repository
public interface ProductViewRepository extends BaseRepository<ProductView, String> {
}
