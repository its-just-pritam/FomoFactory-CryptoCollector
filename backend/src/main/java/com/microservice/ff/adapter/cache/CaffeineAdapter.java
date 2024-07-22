package com.microservice.ff.adapter.cache;

import com.microservice.ff.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@CacheConfig(cacheNames = {Constant.CRYPTOCURRENCY})
public class CaffeineAdapter {

    private final CacheManager cacheManager;

    @Autowired
    public CaffeineAdapter(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void save(String cacheName, String cacheKey, byte[] response) {
        Cache cache = cacheManager.getCache(cacheName);
        if(cache != null) cache.put(cacheKey, response);
        LOGGER.info("[{}.{}] Cache key {} saved to cache: {}", this.getClass().getSimpleName(), "save", cacheKey, cacheName);
    }

    public Object find(String cacheName, String cacheKey) {
        Cache cache = cacheManager.getCache(cacheName);
        if(cache == null) return null;
        else {
            Cache.ValueWrapper valueWrapper = cache.get(cacheKey);
            if (valueWrapper == null) return null;
            else return valueWrapper.get();
        }
    }
}
