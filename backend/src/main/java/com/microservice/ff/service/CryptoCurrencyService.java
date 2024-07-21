package com.microservice.ff.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.ff.model.CryptoCurrency;
import com.microservice.ff.repository.CryptoCurrencyRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class CryptoCurrencyService {

    private final CryptoCurrencyRepo cryptoCurrencyRepo;
    private final ObjectMapper objectMapper;

    @Autowired
    public CryptoCurrencyService(CryptoCurrencyRepo cryptoCurrencyRepo, ObjectMapper objectMapper) {
        this.cryptoCurrencyRepo = cryptoCurrencyRepo;
        this.objectMapper = objectMapper;
    }

    public void addPointData(String data) {

        LOGGER.info("[{}.{}] Processing", this.getClass().getSimpleName(), "addPointData");
        List<CryptoCurrency> listOfModel = new ArrayList<>();
        try {
            JsonNode pointDataObject = objectMapper.readTree(data);
            Iterator<Map.Entry<String, JsonNode>> points = pointDataObject.fields();
            while (points.hasNext()) {
                Map.Entry<String, JsonNode> point = points.next();
                JsonNode cryptoData = point.getValue();

                CryptoCurrency cryptoCurrency = new CryptoCurrency();
                cryptoCurrency.setName(point.getKey());
                cryptoCurrency.setId(UUID.randomUUID());
                cryptoCurrency.setUsd(cryptoData.get("usd").asDouble());
                cryptoCurrency.setUsdMarketCap(cryptoData.get("usd_market_cap").asDouble());
                cryptoCurrency.setUsd24hVol(cryptoData.get("usd_24h_vol").asDouble());
                cryptoCurrency.setUsd24hChange(cryptoData.get("usd_24h_change").asDouble());
                cryptoCurrency.setLastUpdatedAt(cryptoData.get("last_updated_at").asLong());
                listOfModel.add(cryptoCurrency);
            }

            cryptoCurrencyRepo.saveAll(listOfModel);
            LOGGER.info("[{}.{}] Completed", this.getClass().getSimpleName(), "addPointData");
        } catch (JsonProcessingException e) {
            LOGGER.info("[{}.{}] Error in parsing Crypto Data", this.getClass().getSimpleName(), "addPointData", e);
        } catch (Exception e) {
            LOGGER.info("[{}.{}] Unknown error", this.getClass().getSimpleName(), "addPointData", e);

        }
    }

    public List<CryptoCurrency> getPointsByName(String name) {
        return cryptoCurrencyRepo.findTop20ByNameOrderByLastUpdatedAtDesc(name);
    }
}
