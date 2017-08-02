 /**
 * Created by GEH3641 on 19/07/2017.
 */
package com.viseo.c360.cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

 @SpringBootApplication
@ComponentScan(basePackages = {"com.viseo.c360.cv.*"})
public class Application {

     @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
