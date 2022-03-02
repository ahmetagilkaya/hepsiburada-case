package tr.com.hepsiburada.rest_api.repository;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Repository;
import tr.com.hepsiburada.model.document.Product;
import tr.com.hepsiburada.rest_api.repository.base.BaseRepository;

@Repository
public interface ProductRepository extends BaseRepository<Product, String> {

    @Aggregation(pipeline = {"{ $match : { $or : [ { categoryId: ?0 }, { categoryId: ?1 }, { categoryId: ?2 } ] } }",
            "{ $lookup : { from : ORDER_ITEMS, localField : _id, foreignField : productId, as : orderItem } }",
            "{ $lookup : { from : ORDERS, localField : orderId, foreignField : orderId, as : orderItemWithUser } }"
            //"{ $project : { '_id' : 1, 'categoryId' : 1, 'orderedItemCount': { $size: $orderItem } } }",
            //"{ $sort : { orderedItemCount : -1 } }",
            //"{ $limit : 10 }"
    })
    AggregationResults<Object> deneme(String categoryId1, String categoryId2, String categoryId3);

}
