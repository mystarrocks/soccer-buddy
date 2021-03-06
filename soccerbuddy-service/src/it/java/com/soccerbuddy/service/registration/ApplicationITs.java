package com.soccerbuddy.service.registration;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.soccerbuddy.service.Application;

/**
 * A base setup for testing the Spring rest flows in a container.
 * 
 * @author mystarrocks
 */
@RunWith (SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = Application.class)
@WebAppConfiguration
public class ApplicationITs {
  
  /**
   * Captures output from System.out and System.err.
   */
  @Rule
  public OutputCapture outputCapture = new OutputCapture();

  private String profiles;

  /**
   * Sets the active profile.
   * 
   * @throws Exception if the setup could not be completed successfully 
   */
  @Before
  public void init() throws Exception {
    this.profiles = System.getProperty("spring.profiles.active");
  }

  /**
   * Preserves the active profiles on a system property.
   */
  @After
  public void after() {
    if (this.profiles != null) {
      System.setProperty("spring.profiles.active", this.profiles);
    } else {
      System.clearProperty("spring.profiles.active");
    }
  }
  
  /**
   * Tests nothing.
   */
  @Test
  public void contextLoads() {
  }
}