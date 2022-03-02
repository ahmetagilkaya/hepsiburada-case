package tr.com.hepsiburada.rest_api.repository;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Repository;
import tr.com.hepsiburada.model.document.OrderItem;
import tr.com.hepsiburada.model.dto.repository.BestSellerProductsDTO;
import tr.com.hepsiburada.rest_api.repository.base.BaseRepository;

@Repository
public interface OrderItemRepository extends BaseRepository<OrderItem, Integer> {

    @Aggregation(pipeline = {
            "{ $match : { $or : [ { categoryId: ?0 }, { categoryId: ?1 }, { categoryId: ?2 } ] } }",
            "{ $group : { _id : '$productId', users : { $addToSet : '$userId' } } }",
            "{ $project : { _id : 1, usersDistinctCount: { $size : $users } } }",
            "{ $sort : { usersDistinctCount : -1 } }",
            "{ $limit : 10 }"
    })
    AggregationResults<BestSellerProductsDTO> personalizedBestSeller10Products(String categoryId1, String categoryId2, String categoryId3);

    @Aggregation(pipeline = {
            "{ $match : { $or : [ { categoryId: ?0 }, { categoryId: ?1 } ] } }",
            "{ $group : { _id : '$productId', users : { $addToSet : '$userId' } } }",
            "{ $project : { _id : 1, usersDistinctCount: { $size : $users } } }",
            "{ $sort : { usersDistinctCount : -1 } }",
            "{ $limit : 10 }"
    })
    AggregationResults<BestSellerProductsDTO> personalizedBestSeller10Products(String categoryId1, String categoryId2);

    @Aggregation(pipeline = {
            "{ $match : { categoryId: ?0 } }",
            "{ $group : { _id : '$productId', users : { $addToSet : '$userId' } } }",
            "{ $project : { _id : 1, usersDistinctCount: { $size : $users } } }",
            "{ $sort : { usersDistinctCount : -1 } }",
            "{ $limit : 10 }"
    })
    AggregationResults<BestSellerProductsDTO> personalizedBestSeller10Products(String categoryId1);

    @Aggregation(pipeline = {
            "{ $group : { _id : '$productId', users : { $addToSet : '$userId' } } }",
            "{ $project : { _id : 1, usersDistinctCount: { $size : $users } } }",
            "{ $sort : { usersDistinctCount : -1 } }",
            "{ $limit : 10 }"
    })
    AggregationResults<BestSellerProductsDTO> nonPersonalizedBestSeller10Products();

}
