package net.sahin.springvw.subscription;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SubscriptionService {
    private final SubscriptionRepository repo;
    private final Cache<String, Subscription> cache = Caffeine.newBuilder().build();

    @Autowired
    public SubscriptionService(SubscriptionRepository repository) {
        this.repo = repository;
    }

    @PostConstruct
    public void loadCache() {
        repo.findAll().toStream().forEach(w -> cache.put(w.id, w));
    }

}
