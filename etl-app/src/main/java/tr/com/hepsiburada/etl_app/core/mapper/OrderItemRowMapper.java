package tr.com.hepsiburada.etl_app.core.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import tr.com.hepsiburada.model.dto.etl.OrderItemEtlDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class OrderItemRowMapper implements RowMapper<OrderItemEtlDTO> {

    @Override
    public OrderItemEtlDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItemEtlDTO orderItemEtlDTO = new OrderItemEtlDTO();
        orderItemEtlDTO.setId(rs.getInt("id"));
        orderItemEtlDTO.setOrder_id(rs.getBigDecimal("order_id"));
        orderItemEtlDTO.setProduct_id(rs.getString("product_id"));
        orderItemEtlDTO.setQuantity(rs.getBigDecimal("quantity"));
        return orderItemEtlDTO;
    }

}
