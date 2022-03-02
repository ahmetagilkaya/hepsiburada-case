package tr.com.hepsiburada.etl_app.core.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import tr.com.hepsiburada.model.dto.etl.OrderEtlDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class OrderRowMapper implements RowMapper<OrderEtlDTO> {

    @Override
    public OrderEtlDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderEtlDTO orderEtlDTO = new OrderEtlDTO();
        orderEtlDTO.setOrder_id(rs.getBigDecimal("order_id"));
        orderEtlDTO.setUser_id(rs.getString("user_id"));
        return orderEtlDTO;
    }

}
