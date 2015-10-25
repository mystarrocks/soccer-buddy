package com.soccerbuddy.service.registration;

import static com.soccerbuddy.service.registration.TestUtils.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Tests the RESTful APIs exposed by the {@link GroupRegistrationService}.
 * 
 * @author mystarrocks
 */
public class GroupRegistrationServiceIT extends ApplicationITs {
  
  @Autowired private WebApplicationContext context;
  
  private MockMvc mvc = standaloneSetup(new GroupRegistrationService()).build();

  /**
   * Tests the {@link GroupRegistrationService#register(RegisteringGroup)} operation
   * for a status code of {@code 200} after a successful registration of the group
   * attempted by an existing user.
   * 
   * @throws JsonProcessingException if the mock object could not be {@code JSON} serialized
   * @throws Exception if the method under test threw one
   */
  @Test
  public void register_ExistingUser_200() throws JsonProcessingException, Exception {
    RegisteringUser admin = RegisteringUser.builder().userName("mystarrocks").build();
    RegisteringGroup group = RegisteringGroup.builder().
        admin(admin).
        existingUser(true).
        groupName("soccer-buddy").build();
    
    mvc.perform(put("/register/group").
        content(asJsonString(group)).
        contentType(MediaType.APPLICATION_JSON)).
      andExpect(status().isOk()).
      andExpect(content().contentType(MediaType.APPLICATION_JSON)).
      andExpect(jsonPath("$.resource.groupName").value("soccer-buddy"));
  }
  
  /**
   * Tests the {@link GroupRegistrationService#register(RegisteringGroup)} operation
   * for a status code of {@code 200} after a successful registration of the group
   * attempted by a new user.
   * 
   * @throws JsonProcessingException if the mock object could not be {@code JSON} serialized
   * @throws Exception if the method under test threw one
   */
  @Test
  public void register_NewUser_200() throws JsonProcessingException, Exception {
    MockMvc localMvc = MockMvcBuilders.webAppContextSetup(context).build();
    RegisteringUser admin = RegisteringUser.builder().userName("mystarrocks").build();
    RegisteringGroup group = RegisteringGroup.builder().
        admin(admin).
        existingUser(false).
        groupName("soccer-buddy").build();
    
    localMvc.perform(put("/register/group").
        content(asJsonString(group)).
        contentType(MediaType.APPLICATION_JSON)).
      andExpect(status().isOk()).
      andExpect(content().contentType(MediaType.APPLICATION_JSON)).
      andExpect(jsonPath("$.resource.groupName").value("soccer-buddy"));
  }
  
  /**
   * Tests the {@link GroupRegistrationService#register(RegisteringGroup)} operation
   * for a status code of {@code 200} after a successful registration of the group
   * attempted by an existing user.
   * 
   * @throws JsonProcessingException if the mock object could not be {@code JSON} serialized
   * @throws Exception if the method under test threw one
   */
  @Test
  public void unregister_200() throws JsonProcessingException, Exception {
    RegisteringUser admin = RegisteringUser.builder().userName("mystarrocks").build();
    RegisteringGroup group = RegisteringGroup.builder().
        admin(admin).
        existingUser(true).
        groupName("soccer-buddy").build();
    
    mvc.perform(delete("/register/group").
        content(asJsonString(group)).
        contentType(MediaType.APPLICATION_JSON)).
      andExpect(status().isOk()).
      andExpect(content().contentType(MediaType.APPLICATION_JSON)).
      andExpect(jsonPath("$.resource.groupName").value("soccer-buddy"));
  }
  
  /**
   * Tests the {@link GroupRegistrationService#register(RegisteringGroup)} operation
   * for a status code of {@code 200} after a successful registration of the group
   * attempted by an existing user.
   * 
   * @throws JsonProcessingException if the mock object could not be {@code JSON} serialized
   * @throws Exception if the method under test threw one
   */
  @Test
  public void reregister_ExistingGroup_200() throws JsonProcessingException, Exception {
    RegisteringUser admin = RegisteringUser.builder().userName("mystarrocks").build();
    RegisteringGroup group = RegisteringGroup.builder().
        admin(admin).
        existingUser(true).
        groupName("soccer-buddy").build();
    
    mvc.perform(post("/register/group").
        content(asJsonString(group)).
        contentType(MediaType.APPLICATION_JSON)).
      andExpect(status().isOk()).
      andExpect(content().contentType(MediaType.APPLICATION_JSON)).
      andExpect(jsonPath("$.resource.groupName").value("soccer-buddy"));
  }

}
