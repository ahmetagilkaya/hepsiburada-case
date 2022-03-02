package tr.com.hepsiburada.etl_app.core.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import tr.com.hepsiburada.model.dto.etl.ProductEtlDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ProductRowMapper implements RowMapper<ProductEtlDTO> {

    @Override
    public ProductEtlDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductEtlDTO productEtlDTO = new ProductEtlDTO();
        productEtlDTO.setProduct_id(rs.getString("product_id"));
        productEtlDTO.setCategory_id(rs.getString("category_id"));
        return productEtlDTO;
    }

}
