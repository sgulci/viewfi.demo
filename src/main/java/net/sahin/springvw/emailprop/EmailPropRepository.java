package net.sahin.springvw.emailprop;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailPropRepository extends ReactiveMongoRepository<EmailProp, String> {
}
