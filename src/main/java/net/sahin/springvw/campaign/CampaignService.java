package net.sahin.springvw.campaign;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class CampaignService {
    private final CampaignRepository repo;
    private final Cache<String, Campaign> cache = Caffeine.newBuilder().build();

    @Autowired
    public CampaignService(CampaignRepository repository) {
        this.repo = repository;
    }

    public Mono<Campaign> getCampaign(String id){

        Optional<Campaign> campaign = Optional.ofNullable(cache.getIfPresent(id));

        return campaign
                .map(Mono::just)
                .orElseGet(() ->
                        this.repo.findById(id)
                                .doOnNext(c -> cache.put(id, c))
                );
    }


    @PostConstruct
    public void loadCache() {
        repo.findAll().toStream().forEach(w -> cache.put(w.id, w));
    }
}
