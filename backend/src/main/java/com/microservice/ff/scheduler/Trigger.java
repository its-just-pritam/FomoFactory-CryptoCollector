package com.microservice.ff.scheduler;

import com.microservice.ff.scheduler.job.CryptoDataAggregator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Trigger {

    private final CryptoDataAggregator cryptoDataAggregator;

    @Autowired
    public Trigger(CryptoDataAggregator cryptoDataAggregator) {
        this.cryptoDataAggregator = cryptoDataAggregator;
    }

    @Scheduled(initialDelay = 10000, fixedRate = 30000)
    public void run() {
        LOGGER.info("[{}.{}] Executing job", this.getClass().getSimpleName(), "run");
        cryptoDataAggregator.execute();
    }

}
