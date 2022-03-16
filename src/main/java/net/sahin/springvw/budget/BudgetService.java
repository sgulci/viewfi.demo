package net.sahin.springvw.budget;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BudgetService {

    private final BudgetRepository repo;
    private final Cache<String, Budget> cache = Caffeine.newBuilder().build();

    @Autowired
    public BudgetService(BudgetRepository repository) {
        this.repo = repository;
    }

    @PostConstruct
    public void loadCache() {
        repo.findAll().toStream().forEach(w -> cache.put(w.organizationId, w));
    }
}
