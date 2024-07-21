package com.microservice.ff.scheduler.job;

import com.microservice.ff.service.CryptoCurrencyService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CryptoDataAggregator {

    @Qualifier("coinGekoRequestConfig")
    private final Request request;
    private final OkHttpClient okHttpClient;
    private final CryptoCurrencyService cryptoCurrencyService;

    @Autowired
    public CryptoDataAggregator(Request request, OkHttpClient okHttpClient, CryptoCurrencyService cryptoCurrencyService) {
        this.request = request;
        this.okHttpClient = okHttpClient;
        this.cryptoCurrencyService = cryptoCurrencyService;
    }

    @Async
    public void execute() {
        LOGGER.info("[{}.{}] Collecting Crypto Data from: {}", this.getClass().getSimpleName(), "execute", request.url());
        try (Response response = okHttpClient.newCall(request).execute()) {
            if(response.isSuccessful()) {
                String data = response.body().string();
                LOGGER.info("[{}.{}] Obtained Crypto Data: {}", this.getClass().getSimpleName(), "execute", data);
                cryptoCurrencyService.addPointData(data);
            } else throw new RuntimeException(response.body().string());

        } catch (IOException | RuntimeException e) {
            LOGGER.info("[{}.{}] Error collecting Crypto Data", this.getClass().getSimpleName(), "execute", e);

        }
    }

}
