package net.sahin.springvw.subscription;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends ReactiveMongoRepository<Subscription, String> {
}
