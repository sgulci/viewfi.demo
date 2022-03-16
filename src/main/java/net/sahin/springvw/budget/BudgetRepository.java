package net.sahin.springvw.budget;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BudgetRepository extends ReactiveMongoRepository<Budget, String>  {


}
