package com.microservice.ff.scheduler.trigger;

import com.microservice.ff.scheduler.job.CryptoDataAggregator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PollingTrigger {

    private final CryptoDataAggregator cryptoDataAggregator;

    @Autowired
    public PollingTrigger(CryptoDataAggregator cryptoDataAggregator) {
        this.cryptoDataAggregator = cryptoDataAggregator;
    }

    @Scheduled(initialDelay = 5000, fixedRate = 15000)
    public void run() {
        LOGGER.info("[{}.{}] Executing job", this.getClass().getSimpleName(), "run");
        cryptoDataAggregator.execute();
    }

}
