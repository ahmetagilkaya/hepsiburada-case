package tr.com.hepsiburada.etl_app.core.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tr.com.hepsiburada.model.document.Order;
import tr.com.hepsiburada.model.document.OrderItem;
import tr.com.hepsiburada.model.document.Product;
import tr.com.hepsiburada.model.dto.etl.OrderEtlDTO;
import tr.com.hepsiburada.model.dto.etl.OrderItemEtlDTO;
import tr.com.hepsiburada.model.dto.etl.ProductEtlDTO;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class EtlDtoToDocumentMapper {

    public Order mapOrderEtlDtoToOrderDocument(OrderEtlDTO orderEtlDTO) {
        Order order = new Order();
        order.setOrderId(orderEtlDTO.getOrder_id());
        order.setUserId(orderEtlDTO.getUser_id());
        return order;
    }

    public OrderItem mapOrderItemEtlDtoToOrderItemDocument(OrderItemEtlDTO orderItemEtlDTO, String categoryId, String userId) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemEtlDTO.getId());
        orderItem.setOrderId(orderItemEtlDTO.getOrder_id());
        orderItem.setProductId(orderItemEtlDTO.getProduct_id());
        orderItem.setQuantity(orderItemEtlDTO.getQuantity());
        orderItem.setCategoryId(categoryId);
        orderItem.setUserId(userId);
        return orderItem;
    }

    public Product mapProductEtlDtoToProductDocument(ProductEtlDTO productEtlDTO) {
        Product product = new Product();
        product.setProductId(productEtlDTO.getProduct_id());
        product.setCategoryId(productEtlDTO.getCategory_id());
        return product;
    }

}
