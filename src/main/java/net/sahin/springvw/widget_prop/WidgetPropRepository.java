package net.sahin.springvw.widget_prop;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WidgetPropRepository  extends ReactiveMongoRepository<WidgetProp, String> {

}
