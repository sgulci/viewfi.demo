package net.sahin.springvw.coupon;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CouponService {
    private final CouponRepository repo;
    private final Cache<String, Coupon> cache = Caffeine.newBuilder().build();

    @Autowired
    public CouponService(CouponRepository repository) {
        this.repo = repository;
    }

    @PostConstruct
    public void loadCache() {
        repo.findAll().toStream().forEach(w -> cache.put(w.id, w));
    }
}
