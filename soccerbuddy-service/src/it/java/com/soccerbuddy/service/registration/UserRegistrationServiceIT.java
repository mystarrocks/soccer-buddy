package com.soccerbuddy.service.registration;

import static com.soccerbuddy.service.registration.TestUtils.asJsonString;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soccerbuddy.model.RegisteringUser;
import com.soccerbuddy.model.ServiceError;

/**
 * Tests the RESTful APIs exposed by the {@link UserRegistrationService}.
 * 
 * @author mystarrocks
 */
public class UserRegistrationServiceIT extends ApplicationITs {
  
  @Autowired private WebApplicationContext context;
  
  private MockMvc mvc;
  
  @Before
  @Override
  public void init() {
    super.init();
    mvc = webAppContextSetup(context).build();
  }
  
  /**
   * Tests the {@link UserRegistrationService#register(RegisteringUser)} operation
   * for a status code of {@code 200} after a successful registration.
   * 
   * @throws JsonProcessingException if the mock object could not be {@code JSON} serialized
   * @throws Exception if the method under test threw one
   */
  @Test
  public void registerUser_400_ValidationFailure() throws JsonProcessingException, Exception {
    RegisteringUser user = RegisteringUser.builder().
        phoneNumber("123-456-7890").build();
    
    mvc.perform(put("/register/user")
        .content(asJsonString(user))
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.type").value(ServiceError.Type.VALIDATION_ERROR.name()))
      .andExpect(jsonPath("$.description").value(containsString("must match \"\\(\\d{3}\\)-\\d{3}-\\d{4}\"")))
      .andExpect(jsonPath("$.description").value(containsString("may not be empty")))
      .andExpect(jsonPath("$.description").value(containsString("may not be empty")));
  }

  /**
   * Tests the {@link UserRegistrationService#register(RegisteringUser)} operation
   * for a status code of {@code 200} after a successful registration.
   * 
   * @throws JsonProcessingException if the mock object could not be {@code JSON} serialized
   * @throws Exception if the method under test threw one
   */
  @Test
  public void registerUser_200() throws JsonProcessingException, Exception {
    RegisteringUser user = RegisteringUser.builder().
        userName("mystarrocks").
        emailId("sunilceg7@gmail.com").
        phoneNumber("(123)-456-7890").build();
    
    mvc.perform(put("/register/user").
        content(asJsonString(user)).
        contentType(MediaType.APPLICATION_JSON)).
      //andDo(print()).
      andExpect(status().isOk()).
      andExpect(content().contentType(MediaType.APPLICATION_JSON)).
      andExpect(jsonPath("$.resource.userName").value("mystarrocks"));
    
    //assertThat(user.isAudited(), is(true));
  }
  
  /**
   * Tests the {@link UserRegistrationService#unregister(RegisteringUser)} operation
   * for a status code of {@code 200} after a successful unregistration.
   * 
   * @throws JsonProcessingException if the mock object could not be {@code JSON} serialized
   * @throws Exception if the method under test threw one
   */
  @Test
  public void unregisterUser_200() throws JsonProcessingException, Exception {
    RegisteringUser user = RegisteringUser.builder().
        userName("mystarrocks").
        emailId("sunilceg7@gmail.com").
        phoneNumber("(123)-456-7890").build();
    
    mvc.perform(delete("/register/user").
        content(asJsonString(user)).
        contentType(MediaType.APPLICATION_JSON)).
      andExpect(status().isOk()).
      andExpect(content().contentType(MediaType.APPLICATION_JSON)).
      andExpect(jsonPath("$.resource.userName").value("mystarrocks"));
  }
  
  /**
   * Tests the {@link UserRegistrationService#reregister(RegisteringUser)} operation
   * for a status code of {@code 200} after a successful reregistration.
   * 
   * @throws JsonProcessingException if the mock object could not be {@code JSON} serialized
   * @throws Exception if the method under test threw one
   */
  @Test
  public void reregisterUser_200() throws JsonProcessingException, Exception {
    RegisteringUser user = RegisteringUser.builder().
        userName("mystarrocks").
        emailId("sunilceg7@gmail.com").build();
    
    mvc.perform(post("/register/user").
        content(asJsonString(user)).
        contentType(MediaType.APPLICATION_JSON)).
      andExpect(status().isOk()).
      andExpect(content().contentType(MediaType.APPLICATION_JSON)).
      andExpect(jsonPath("$.resource.userName").value("mystarrocks"));
  }
}
