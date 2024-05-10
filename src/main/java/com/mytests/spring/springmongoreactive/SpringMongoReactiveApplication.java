package com.mytests.spring.springmongoreactive;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringMongoReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMongoReactiveApplication.class, args);
    }
@Bean
    public CommandLineRunner commandLineRunner(UserReactiveService service) {
        return args -> {
            System.out.println("--------------------------------------");
            service.display();
            System.out.println("--------------------------------------");
        };
    }
}
