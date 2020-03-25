package com.chuck.norris.joke.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.chuck.norris.joke.*")
public class ChuckNorrisJokeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChuckNorrisJokeApplication.class, args);
    }


}
