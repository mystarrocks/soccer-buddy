package com.soccerbuddy.service;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.googlecode.objectify.ObjectifyFilter;

/**
 * The spring application runner.
 * 
 * @author mystarrocks
 * @since 1.0
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Application {
  
  /**
   * Marks the start of the application execution.
   * 
   * @param args  not used
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  /**
   * Registers an {@code Objectify} filter to cleanup any thread-local
   * contexts and pending async operations that remain at the end of a
   * request.
   * 
   * @return the {@code Objectify} filter's registration bean
   * @see <a href="https://github.com/objectify/objectify/wiki/Setup#enable-objectifyfilter-for-your-requests">
   * Enable ObjectifyFilter for your requests</a>
   */
  @Bean
  public FilterRegistrationBean ofyFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(ofyFilter());
    registration.setName("ObjectifyFilter");
    return registration;
  }

  /**
   * Returns an {@code Objectify} filter to cleanup any thread-local
   * contexts and pending async operations that remain at the end of a
   * request.
   * 
   * @return an {@code Objectify} filter to cleanup any thread-local
   * contexts and pending async operations that remain at the end of a
   * request
   * @see <a href="https://github.com/objectify/objectify/wiki/Setup#enable-objectifyfilter-for-your-requests">
   * Enable ObjectifyFilter for your requests</a>
   */
  @Bean(name = "ObjectifyFilter")
  public Filter ofyFilter() {
    return new ObjectifyFilter();
  }
}