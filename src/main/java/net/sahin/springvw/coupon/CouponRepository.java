package net.sahin.springvw.coupon;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends ReactiveMongoRepository<Coupon, String> {

}
