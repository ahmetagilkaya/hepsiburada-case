package tr.com.hepsiburada.etl_app.scheduled;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tr.com.hepsiburada.etl_app.service.etl.EtlService;

@Component
@RequiredArgsConstructor
public class ScheduledBatchProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledBatchProcess.class);

    @Qualifier("orderEtlService")
    private final EtlService orderEtlService;
    @Qualifier("orderItemEtlService")
    private final EtlService orderItemEtlService;
    @Qualifier("productEtlService")
    private final EtlService productEtlService;

    @Scheduled(fixedDelayString = "${app.batch.period}")
    public void orderCollectionEtlProcess() {
        LOGGER.info("Order etl process started.");
        orderEtlService.runEtlProcess();
        LOGGER.info("Order etl process finished.");
    }

    @Scheduled(fixedDelayString = "${app.batch.period}")
    public void orderItemCollectionEtlProcess() {
        LOGGER.info("OrderItem etl process started.");
        orderItemEtlService.runEtlProcess();
        LOGGER.info("OrderItem etl process finished.");
    }

    @Scheduled(fixedDelayString = "${app.batch.period}")
    public void productCollectionEtlProcess() {
        LOGGER.info("Product etl process started.");
        productEtlService.runEtlProcess();
        LOGGER.info("Product etl process finished.");
    }

}
