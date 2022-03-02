package tr.com.hepsiburada.model.dto.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestSellerProductsDTO {

    private String _id;
    private Integer usersDistinctCount;

}
