package net.sahin.springvw.promotion;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PromotionCodeService {

    private final PromotionCodeRepository repo;
    private final Cache<String, PromotionCode> cache = Caffeine.newBuilder().build();

    @Autowired
    public PromotionCodeService(PromotionCodeRepository repository) {
        this.repo = repository;
    }

    @PostConstruct
    public void loadCache() {
        repo.findAll().toStream().forEach(w -> cache.put(w.id, w));
    }
}
