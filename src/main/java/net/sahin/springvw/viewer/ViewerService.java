package net.sahin.springvw.viewer;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ViewerService {

    private final ViewerRepository repo;
    private final Cache<String, Viewer> cache = Caffeine.newBuilder().build();

    @Autowired
    public ViewerService(ViewerRepository repository) {
        this.repo = repository;
    }

    @PostConstruct
    public void loadCache() {
        repo.findAll().toStream().forEach(w -> cache.put(w.id, w));
    }
}
