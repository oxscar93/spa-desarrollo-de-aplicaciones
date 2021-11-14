package ar.edu.unq.desapp.grupoH022021.backenddesappapi.schedules;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CriptoScheduler {
    @CacheEvict(allEntries = true, value="criptos")
    @Scheduled(fixedRate = 60000)
    public void evictAllcachesAtInterval() { }
}
