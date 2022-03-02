package tr.com.hepsiburada.rest_api.repository;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Repository;
import tr.com.hepsiburada.model.document.ProductView;
import tr.com.hepsiburada.model.dto.repository.MostThreeCategoriesDTO;
import tr.com.hepsiburada.rest_api.repository.base.BaseRepository;

import java.util.List;

@Repository
public interface ProductViewRepository extends BaseRepository<ProductView, String> {
    List<ProductView> findTop10ByUserIdOrderByViewTimeDesc(String userId);

    void deleteByUserIdAndProductId(String userId, String productId);

    @Aggregation(pipeline = {"{ $match : { userId : ?0 } }",
            "{ $lookup : { from : PRODUCTS, localField : productId, foreignField : _id, as : category } }",
            "{ $group : { _id : '$category.categoryId', categoryCount : { $sum : 1 } } }",
            "{ $sort : { categoryCount : -1 } }",
            "{ $limit : 3 }"
    })
    AggregationResults<MostThreeCategoriesDTO> mostThreeCategoriesInUserHistory(String userId);

}
