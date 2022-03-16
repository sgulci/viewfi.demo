package net.sahin.springvw.campaign;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends ReactiveMongoRepository<Campaign, String> {

}
