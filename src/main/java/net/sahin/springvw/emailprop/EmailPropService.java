package net.sahin.springvw.emailprop;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class EmailPropService {

    private final EmailPropRepository repo;
    private final Cache<String, EmailProp> cache = Caffeine.newBuilder().build();

    @Autowired
    public EmailPropService(EmailPropRepository repository) {
        this.repo = repository;
    }

    @PostConstruct
    public void loadCache() {
        repo.findAll().toStream().forEach(w -> cache.put(w.id, w));
    }
}
