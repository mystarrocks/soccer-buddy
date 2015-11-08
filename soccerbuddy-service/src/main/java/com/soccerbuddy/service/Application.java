package com.soccerbuddy.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * The spring application runner.
 * 
 * @author mystarrocks
 * @since 1.0
 */
@SpringBootApplication
@EnableAspectJAutoProxy (proxyTargetClass = true)
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}