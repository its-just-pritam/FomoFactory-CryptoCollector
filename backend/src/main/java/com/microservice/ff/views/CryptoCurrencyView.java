package com.microservice.ff.views;

import com.microservice.ff.model.CryptoCurrency;
import com.microservice.ff.service.CryptoCurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class CryptoCurrencyView {

    private final CryptoCurrencyService cryptoCurrencyService;

    @Autowired
    public CryptoCurrencyView(CryptoCurrencyService cryptoCurrencyService) {
        this.cryptoCurrencyService = cryptoCurrencyService;
    }

    @GetMapping(value="/crypto/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPoints(@PathVariable String name) {
        LOGGER.info("[{}.{}] Processing", this.getClass().getSimpleName(), "getPoints");
        List<CryptoCurrency> response = cryptoCurrencyService.getPointsByName(name);
        LOGGER.info("[{}.{}] Completed", this.getClass().getSimpleName(), "getPoints");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
