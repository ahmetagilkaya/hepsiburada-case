package tr.com.hepsiburada.model.dto.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MostThreeCategoriesDTO {

    private List<String> _id;
    private Integer categoryCount;

}
