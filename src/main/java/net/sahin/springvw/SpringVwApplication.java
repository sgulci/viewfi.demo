package net.sahin.springvw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SpringVwApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringVwApplication.class, args);
    }

}
