package com.soccerbuddy.service.registration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A base setup for testing the Spring rest flows in a container.
 * 
 * @author mystarrocks
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTests {
  
  /**
   * Tests nothing.
   */
  @Test
  public void contextLoads() {
  }
}