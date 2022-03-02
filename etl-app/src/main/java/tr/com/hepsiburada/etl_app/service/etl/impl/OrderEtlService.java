package tr.com.hepsiburada.etl_app.service.etl.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tr.com.hepsiburada.core.mapper.DTOMapper;
import tr.com.hepsiburada.etl_app.core.helper.ItemReaderHelper;
import tr.com.hepsiburada.etl_app.core.helper.ItemWriterHelper;
import tr.com.hepsiburada.etl_app.core.mapper.EtlDtoToDocumentMapper;
import tr.com.hepsiburada.etl_app.core.mapper.OrderRowMapper;
import tr.com.hepsiburada.etl_app.service.etl.EtlService;
import tr.com.hepsiburada.model.document.Order;
import tr.com.hepsiburada.model.dto.etl.OrderEtlDTO;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Objects;

@Service("orderEtlService")
@RequiredArgsConstructor
public class OrderEtlService implements EtlService {

    public static final String ORDER_COLLECTION_NAME = "ORDERS";
    public static final String ORDER_READER_NAME = "orderJdcbCursorItemReader";
    public static final String FIND_ALL_ORDERS_SQL = "SELECT order_id, user_id FROM ORDERS ORDER BY order_id ASC";

    @Value("${app.batch.size}")
    private Integer batchSize;

    private final ItemWriterHelper itemWriterHelper;
    private final ItemReaderHelper itemReaderHelper;
    private final DataSource dataSource;
    private final EtlDtoToDocumentMapper etlDtoToDocumentMapper;
    private final OrderRowMapper orderRowMapper;

    @Override
    public void runEtlProcess() {
        ArrayList<Order> readedOrderList = new ArrayList<>();
        ExecutionContext orderExecutionContext = new ExecutionContext();
        JdbcCursorItemReader<OrderEtlDTO> orderItemReader = itemReaderHelper.createJdbcCursorItemReader(dataSource, ORDER_READER_NAME, FIND_ALL_ORDERS_SQL, orderRowMapper);
        ItemWriter<Order> orderItemWriter = itemWriterHelper.createMongoItemWriter(ORDER_COLLECTION_NAME);
        try {
            orderItemReader.open(orderExecutionContext);
            OrderEtlDTO readedOrderEtlDTO = orderItemReader.read();
            while (Objects.nonNull(readedOrderEtlDTO)) {
                readedOrderList.add(etlDtoToDocumentMapper.mapOrderEtlDtoToOrderDocument(readedOrderEtlDTO));

                if (readedOrderList.size() >= batchSize) {
                    itemWriterHelper.writeAndClearList(orderItemWriter, readedOrderList);
                }

                readedOrderEtlDTO = orderItemReader.read();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (readedOrderList.size() > 0) {
                itemWriterHelper.writeAndClearList(orderItemWriter, readedOrderList);
            }
            orderItemReader.close();
        }
    }

}
