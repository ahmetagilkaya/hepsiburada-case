package tr.com.hepsiburada.rest_api.service;

import tr.com.hepsiburada.model.document.base.BaseDocument;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T extends BaseDocument, ID extends Serializable> extends BaseService {

    T findById(ID documentId);

    List<T> findAll();

    T save(T document);

    T update(T document);

    void delete(T document);

    void deleteById(ID document);

}
