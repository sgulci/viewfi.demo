package net.sahin.springvw.widget_prop;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class WidgetPropService {
    private final WidgetPropRepository repo;
    private final Cache<String, WidgetProp> cache = Caffeine.newBuilder().build();

    @Autowired
    public WidgetPropService(WidgetPropRepository repository) {
        this.repo = repository;
    }

    @PostConstruct
    public void loadCache() {
        repo.findAll().toStream().forEach(w -> cache.put(w.id, w));
    }
}
