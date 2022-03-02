package tr.com.hepsiburada.view_producer_app.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import tr.com.hepsiburada.core.helper.FileHelper;
import tr.com.hepsiburada.model.dto.kafka.ProductViewKafkaDTO;
import tr.com.hepsiburada.view_producer_app.core.util.FileUtil;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class FileConfig {

    @Value("classpath:product-views.json")
    Resource productViewFile;

    private final FileHelper fileHelper;
    private final ObjectMapper objectMapper;

    @Bean
    public Queue<ProductViewKafkaDTO> productViewQueue() {
        Stream<String> productViewStream = fileHelper.readFileLineByLine(FileUtil.getAbsolutePathFromResource(productViewFile));
        return productViewStream
                .map(productView -> {
                    try {
                        return objectMapper.readValue(productView, ProductViewKafkaDTO.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toCollection(LinkedList::new));
    }

}
