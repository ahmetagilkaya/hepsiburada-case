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
import tr.com.hepsiburada.etl_app.core.mapper.OrderItemRowMapper;
import tr.com.hepsiburada.etl_app.core.mapper.OrderRowMapper;
import tr.com.hepsiburada.etl_app.core.mapper.ProductRowMapper;
import tr.com.hepsiburada.etl_app.service.etl.EtlService;
import tr.com.hepsiburada.model.document.OrderItem;
import tr.com.hepsiburada.model.dto.etl.OrderEtlDTO;
import tr.com.hepsiburada.model.dto.etl.OrderItemEtlDTO;
import tr.com.hepsiburada.model.dto.etl.ProductEtlDTO;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service("orderItemEtlService")
@RequiredArgsConstructor
public class OrderItemEtlService implements EtlService {

    public static final String ORDER_ITEM_COLLECTION_NAME = "ORDER_ITEMS";
    public static final String ORDER_ITEM_READER_NAME = "orderItemJdcbCursorItemReader";
    public static final String FIND_ALL_ORDER_ITEMS_SQL = "SELECT id, order_id, product_id, quantity FROM ORDER_ITEMS ORDER BY id ASC";

    @Value("${app.batch.size}")
    private Integer batchSize;

    private final ItemWriterHelper itemWriterHelper;
    private final ItemReaderHelper itemReaderHelper;
    private final DataSource dataSource;
    private final EtlDtoToDocumentMapper etlDtoToDocumentMapper;
    private final OrderItemRowMapper orderItemRowMapper;
    private final ProductRowMapper productRowMapper;
    private final OrderRowMapper orderRowMapper;

    @Override
    public void runEtlProcess() {
        ArrayList<OrderItem> readedOrderItemList = new ArrayList<>();
        ExecutionContext orderItemExecutionContext = new ExecutionContext();
        JdbcCursorItemReader<OrderItemEtlDTO> orderItemItemReader = itemReaderHelper.createJdbcCursorItemReader(dataSource, ORDER_ITEM_READER_NAME, FIND_ALL_ORDER_ITEMS_SQL, orderItemRowMapper);
        ItemWriter<OrderItem> orderItemItemWriter = itemWriterHelper.createMongoItemWriter(ORDER_ITEM_COLLECTION_NAME);
        Map<String, String> categoryIdMap = readAllProductsCategoryId();
        Map<BigDecimal, String> userIdMap = readAllOrdersUserId();

        try {
            orderItemItemReader.open(orderItemExecutionContext);
            OrderItemEtlDTO readedOrderItemEtlDTO = orderItemItemReader.read();
            while (Objects.nonNull(readedOrderItemEtlDTO)) {
                readedOrderItemList.add(etlDtoToDocumentMapper.mapOrderItemEtlDtoToOrderItemDocument(readedOrderItemEtlDTO, categoryIdMap.get(readedOrderItemEtlDTO.getProduct_id()), userIdMap.get(readedOrderItemEtlDTO.getOrder_id())));

                if (readedOrderItemList.size() >= batchSize) {
                    itemWriterHelper.writeAndClearList(orderItemItemWriter, readedOrderItemList);
                }

                readedOrderItemEtlDTO = orderItemItemReader.read();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (readedOrderItemList.size() > 0) {
                itemWriterHelper.writeAndClearList(orderItemItemWriter, readedOrderItemList);
            }
            orderItemItemReader.close();
        }
    }

    private Map<String, String> readAllProductsCategoryId() {
        Map<String, String> categoryIdMap = new HashMap<>();
        ExecutionContext productExecutionContext = new ExecutionContext();
        JdbcCursorItemReader<ProductEtlDTO> productItemReader = itemReaderHelper.createJdbcCursorItemReader(dataSource, ProductEtlService.PRODUCT_READER_NAME, ProductEtlService.FIND_ALL_PRODUCTS_SQL, productRowMapper);
        productItemReader.open(productExecutionContext);
        try {
            ProductEtlDTO readedProductEtlDTO = productItemReader.read();
            while (Objects.nonNull(readedProductEtlDTO)) {
                categoryIdMap.put(readedProductEtlDTO.getProduct_id(), readedProductEtlDTO.getCategory_id());
                readedProductEtlDTO = productItemReader.read();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            productItemReader.close();
        }
        return categoryIdMap;
    }

    private Map<BigDecimal, String> readAllOrdersUserId() {
        Map<BigDecimal, String> userIdMap = new HashMap<>();
        ExecutionContext orderExecutionContext = new ExecutionContext();
        JdbcCursorItemReader<OrderEtlDTO> orderItemReader = itemReaderHelper.createJdbcCursorItemReader(dataSource, OrderEtlService.ORDER_READER_NAME, OrderEtlService.FIND_ALL_ORDERS_SQL, orderRowMapper);
        orderItemReader.open(orderExecutionContext);
        try {
            OrderEtlDTO readedOrderEtlDTO = orderItemReader.read();
            while (Objects.nonNull(readedOrderEtlDTO)) {
                userIdMap.put(readedOrderEtlDTO.getOrder_id(), readedOrderEtlDTO.getUser_id());
                readedOrderEtlDTO = orderItemReader.read();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            orderItemReader.close();
        }
        return userIdMap;
    }

}
