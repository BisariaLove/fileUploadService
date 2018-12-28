package com.leo.solutions;
/*
 * @author love.bisaria on 27/12/18
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"com.leo.solutions"}
        //,excludeFilters ={ @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value=MainApplication.class)}
)
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MainApplication.class);

        app.run(args);
    }
}
