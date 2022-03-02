package tr.com.hepsiburada.rest_api.repository.base;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import tr.com.hepsiburada.model.document.base.BaseDocument;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T extends BaseDocument, ID extends Serializable> extends MongoRepository<T, ID> {
}
