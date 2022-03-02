package tr.com.hepsiburada.etl_app.service.etl.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tr.com.hepsiburada.etl_app.core.helper.ItemReaderHelper;
import tr.com.hepsiburada.etl_app.core.helper.ItemWriterHelper;
import tr.com.hepsiburada.etl_app.core.mapper.EtlDtoToDocumentMapper;
import tr.com.hepsiburada.etl_app.core.mapper.ProductRowMapper;
import tr.com.hepsiburada.etl_app.service.etl.EtlService;
import tr.com.hepsiburada.model.document.Product;
import tr.com.hepsiburada.model.dto.etl.ProductEtlDTO;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Objects;

@Service("productEtlService")
@RequiredArgsConstructor
public class ProductEtlService implements EtlService {

    public static final String PRODUCT_COLLECTION_NAME = "PRODUCTS";
    public static final String PRODUCT_READER_NAME = "productJdcbCursorItemReader";
    public static final String FIND_ALL_PRODUCTS_SQL = "SELECT product_id, category_id FROM PRODUCTS ORDER BY product_id ASC";

    @Value("${app.batch.size}")
    private Integer batchSize;

    private final ItemWriterHelper itemWriterHelper;
    private final ItemReaderHelper itemReaderHelper;
    private final DataSource dataSource;
    private final EtlDtoToDocumentMapper etlDtoToDocumentMapper;
    private final ProductRowMapper productRowMapper;

    @Override
    public void runEtlProcess() {
        ArrayList<Product> readedProductList = new ArrayList<>();
        ExecutionContext productExecutionContext = new ExecutionContext();
        JdbcCursorItemReader<ProductEtlDTO> productItemReader = itemReaderHelper.createJdbcCursorItemReader(dataSource, PRODUCT_READER_NAME, FIND_ALL_PRODUCTS_SQL, productRowMapper);
        ItemWriter<Product> productItemWriter = itemWriterHelper.createMongoItemWriter(PRODUCT_COLLECTION_NAME);
        try {
            productItemReader.open(productExecutionContext);
            ProductEtlDTO readedProductEtlDTO = productItemReader.read();
            while (Objects.nonNull(readedProductEtlDTO)) {
                readedProductList.add(etlDtoToDocumentMapper.mapProductEtlDtoToProductDocument(readedProductEtlDTO));

                if (readedProductList.size() >= batchSize) {
                    itemWriterHelper.writeAndClearList(productItemWriter, readedProductList);
                }

                readedProductEtlDTO = productItemReader.read();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (readedProductList.size() > 0) {
                itemWriterHelper.writeAndClearList(productItemWriter, readedProductList);
            }
            productItemReader.close();
        }
    }

}
