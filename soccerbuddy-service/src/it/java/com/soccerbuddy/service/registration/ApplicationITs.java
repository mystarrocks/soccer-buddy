package com.soccerbuddy.service.registration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.soccerbuddy.service.Application;

/**
 * A base setup for testing the Spring rest flows in a container.
 * 
 * @author mystarrocks
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationITs {
  
  /**
   * Tests nothing.
   */
  @Test
  public void contextLoads() {
  }
}