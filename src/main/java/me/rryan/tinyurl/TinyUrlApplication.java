package me.rryan.tinyurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class TinyUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinyUrlApplication.class, args);
    }

}
 