package com.soccerbuddy.service.registration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class TestUtils {
  
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
  static String asJsonString(final Object obj) throws JsonProcessingException {
    final ObjectMapper mapper = new ObjectMapper();
    final String jsonContent = mapper.writeValueAsString(obj);
    return jsonContent;
  }
}
