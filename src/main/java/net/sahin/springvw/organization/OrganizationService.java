package net.sahin.springvw.organization;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class OrganizationService {

    private final OrganizationRepository repo;
    private final Cache<String, Organization> cache = Caffeine.newBuilder().build();

    @Autowired
    public OrganizationService(OrganizationRepository repository) {
        this.repo = repository;
    }

    @PostConstruct
    public void loadCache() {
        repo.findAll().toStream().forEach(w -> cache.put(w.id, w));
    }

}
