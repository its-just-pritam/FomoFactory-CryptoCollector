package com.microservice.ff.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "CryptoCurrency")
public class CryptoCurrency {

    @Id
    private UUID id;
    private String name;
    private double usd;
    private double usdMarketCap;
    private double usd24hVol;
    private double usd24hChange;
    private long lastUpdatedAt;

}
