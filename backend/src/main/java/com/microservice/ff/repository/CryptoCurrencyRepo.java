package com.microservice.ff.repository;

import com.microservice.ff.model.CryptoCurrency;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface CryptoCurrencyRepo extends MongoRepository<CryptoCurrency, UUID> {
    List<CryptoCurrency> findTop20ByNameOrderByLastUpdatedAtDesc(String name);
}
