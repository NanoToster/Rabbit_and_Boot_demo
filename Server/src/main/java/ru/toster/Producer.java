package ru.toster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ivan Rovenskiy
 * 01 November 2020
 */
@SpringBootApplication
public class Producer {
    public static void main(String[] args) {
        SpringApplication.run(Producer.class, args).close();
    }
}
