package com.soccerbuddy.service.registration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests the RESTful APIs exposed by the {@link RegistrationService}.
 * 
 * @author mystarrocks
 *
 */
public class RegistrationServiceTest {
  private MockMvc mvc = standaloneSetup(new RegistrationService()).build();

  /**
   * Tests the {@link RegistrationService#registerUser(RegisteringUser)} operation
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
        password("password?").
        phoneNumber("123-456-7890").build();
    
    mvc.perform(post("/register/user").
        content(asJsonString(user)).
        contentType(MediaType.APPLICATION_JSON)).
      andExpect(status().isOk()).
      andExpect(content().contentType(MediaType.APPLICATION_JSON)).
      andExpect(jsonPath("$.userName").value("mystarrocks"));
  }
  
  /**
   * Tests the {@link RegistrationService#registerGroup(RegisteringGroup)} operation
   * for a status code of {@code 200} after a successful registration of the group
   * attempted by an existing user.
   * 
   * @throws JsonProcessingException if the mock object could not be {@code JSON} serialized
   * @throws Exception if the method under test threw one
   */
  @Test
  public void registerGroup_ExistingUser_200() throws JsonProcessingException, Exception {
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
      andExpect(jsonPath("$.groupName").value("soccer-buddy"));
  }
  
  /**
   * Tests the {@link RegistrationService#registerGroup(RegisteringGroup)} operation
   * for a status code of {@code 200} after a successful registration of the group
   * attempted by an existing user.
   * 
   * @throws JsonProcessingException if the mock object could not be {@code JSON} serialized
   * @throws Exception if the method under test threw one
   */
  @Test
  public void registerGroup_NewUser_200() throws JsonProcessingException, Exception {
    RegisteringUser admin = RegisteringUser.builder().userName("mystarrocks").build();
    RegisteringGroup group = RegisteringGroup.builder().
        admin(admin).
        existingUser(false).
        groupName("soccer-buddy").build();
    
    mvc.perform(post("/register/group").
        content(asJsonString(group)).
        contentType(MediaType.APPLICATION_JSON)).
      andExpect(status().isOk()).
      andExpect(content().contentType(MediaType.APPLICATION_JSON)).
      andExpect(jsonPath("$.groupName").value("soccer-buddy"));
  }

  /**
   * Serializes the given object to a {@code JSON} string and returns.
   * 
   * <p>
   * TODO: move this out if it proves to be useful
   * 
   * @param obj  the object to be {@code JSON} serialized
   * @return the serialized {@code JSON} text
   * @throws JsonProcessingException if the serialization failed
   */
  private static String asJsonString(final Object obj) throws JsonProcessingException {
    final ObjectMapper mapper = new ObjectMapper();
    final String jsonContent = mapper.writeValueAsString(obj);
    return jsonContent;
  }
}
