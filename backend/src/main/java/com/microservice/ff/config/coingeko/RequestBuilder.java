package com.microservice.ff.config.coingeko;

import okhttp3.HttpUrl;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration("coinGekoRequestConfig")
public class RequestBuilder {

    @Value("${coin.geko.host}")
    private String host;
    @Value("${coin.geko.path.list}")
    private String path;
    @Value("${coin.geko.coins}")
    private String coins;
    @Value("${coin.geko.currency}")
    private String currency;

    @Bean
    public Request generate() {

        return new Request.Builder()
                .url(new HttpUrl.Builder()
                        .scheme("https")
                        .host(host)
                        .addPathSegments(path)
                        .addQueryParameter("ids", coins)
                        .addQueryParameter("vs_currencies", currency)
                        .addQueryParameter("include_market_cap", String.valueOf(true))
                        .addQueryParameter("include_24hr_vol", String.valueOf(true))
                        .addQueryParameter("include_24hr_change", String.valueOf(true))
                        .addQueryParameter("include_last_updated_at", String.valueOf(true))
                        .build())
                .get()
                .build();
    }
}
