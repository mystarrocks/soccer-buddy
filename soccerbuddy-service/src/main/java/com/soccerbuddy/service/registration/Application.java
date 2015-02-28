package com.soccerbuddy.service.registration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The spring application runner.
 * 
 * @author mystarrocks
 * @since 1.0
 */
@ComponentScan
@EnableAutoConfiguration
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}