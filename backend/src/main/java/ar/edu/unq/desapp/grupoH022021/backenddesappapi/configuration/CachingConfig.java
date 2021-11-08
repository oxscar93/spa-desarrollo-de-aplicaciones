package ar.edu.unq.desapp.grupoH022021.backenddesappapi.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@EnableCaching(proxyTargetClass = true)
@Component
public class CachingConfig {

    CacheManager cache;

    @Bean
    public CacheManager cacheManager() {
        cache = new ConcurrentMapCacheManager("criptos", "prices");
        return cache;
    }

    @Scheduled(fixedRate = 60000)
    public void evictAllcachesAtIntervals() {
        evictAllCaches();
    }

    public void evictAllCaches() {
        cache.getCacheNames().stream()
                .forEach(cacheName -> cache.getCache(cacheName).clear());
    }
}
