package net.sahin.springvw.promotion;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionCodeRepository extends ReactiveMongoRepository<PromotionCode, String> {

}
