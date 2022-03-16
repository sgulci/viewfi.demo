package net.sahin.springvw.viewer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewerRepository  extends ReactiveMongoRepository<Viewer, String> {
}
