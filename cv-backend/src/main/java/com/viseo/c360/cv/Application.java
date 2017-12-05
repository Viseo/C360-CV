/**
 * Created by GEH3641 on 19/07/2017.
 */
package com.viseo.c360.cv;

import com.viseo.c360.cv.amqp.ConsumerMessageHandler;
import com.viseo.c360.cv.converters.UserToDtoConverter;
import com.viseo.c360.cv.converters.UserToEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.viseo.c360.cv.*"})
@EntityScan("com.viseo.c360.cv.models.entities")
@EnableJpaRepositories("com.viseo.c360.cv.repositories")
public class Application {

    @Autowired
    private Environment environment;

    @Bean
    public UserToEntityConverter userToEntityConverter() {
        return new UserToEntityConverter();
    }


    @Bean
    public UserToDtoConverter userToDtoConverter() {
        return new UserToDtoConverter();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
