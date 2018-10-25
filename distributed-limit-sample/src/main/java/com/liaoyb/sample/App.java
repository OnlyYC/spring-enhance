package com.liaoyb.sample;

import com.liaoyb.limit.annotation.EnableLimit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liaoyb
 * @date 2018-10-25 15:10
 */
@SpringBootApplication
@EnableLimit
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
