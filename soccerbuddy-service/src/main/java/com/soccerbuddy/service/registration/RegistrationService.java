package com.soccerbuddy.service.registration;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soccerbuddy.data.DataStoreException;
import com.soccerbuddy.model.Resource;
import com.soccerbuddy.model.Result;

/**
 * A RESTful web service that manages registration and unregistration of various
 * entities in the application.
 * 
 * <p>
 * All the registration/unregistration implementations are expected to be a sub context of 
 * the root context: {@code /register} (the register as a noun, i.e a resource) like:
 * <tt>/register/x</tt>, where '{@code x}' is the exact context of the operation. So these operations
 * may resort to differentiating the action being taken based on the HTTP verb they support. Here's
 * a mapping of the operations and the verb that they may choose to use:
 * 
 * <p>
 * <table border="1">
 *   <tr>
 *     <th>Operation</th> <th>HTTP Verb</th>
 *   </tr>
 *   <tr>
 *     <td>Register</td> <td>PUT</td>
 *   </tr>
 *   <tr>
 *     <td>Unregister</td> <td>DELETE</td>
 *   </tr>
 *   <tr>
 *     <td>Reregister</td> <td>POST</td>
 *   </tr>
 * </table>
 * 
 * <p>
 * All the operations, unless specified otherwise explicitly:
 * 
 * <ul>
 *   <li>consume and produce {@code JSON} (see {@link MediaType#APPLICATION_JSON}) content</li>
 *   <li>accept the entity as a {@code @RequestBody} and return a {@code @ResponseBody} consisting
 *   of the result of the registration (the entity itself) and certain headers</li> 
 * </ul> 
 * 
 * @param <R>  the entity being registered/unregistered
 * 
 * @author mystarrocks
 */
interface RegistrationService<R extends Resource> {
  
  /**
   * Registers the given entity by persisting them in the appropriate data store
   * providing it the access to interact with the system further.
   * 
   * @param registeringEntity  the entity attempting to register
   * @return the result of registration
   * @throws DataStoreException if an invalid set of data was passed in to perform registration
   * (like, say, an already registered and active entity) 
   */
  public @ResponseBody ResponseEntity<Result<R>> register(@RequestBody R registeringEntity) throws DataStoreException;
  
  /**
   * Unregisters the given entity by updating the status of the registration of the entity in the 
   * appropriate data store preventing it the access to interact with the system further barring
   * a re-registration.
   * 
   * @param unregisteringEntity  the entity attempting to unregister
   * @return the result of unregistration
   * @throws DataStoreException if an invalid set of data was passed in to perform unregistration
   * (like, say, an already unregistered and unactive entity)
   */
  public @ResponseBody ResponseEntity<Result<R>> unregister(@RequestBody R unregisteringEntity) throws DataStoreException;
  
  /**
   * Re-registers the given entity by updating the status of the previous registration of the entity in the 
   * appropriate data store providing it the access to interact with the system further.
   * 
   * @param reregisteringEntity  the entity attempting to reregister
   * @return the result of unregistration
   * @throws DataStoreException if an invalid set of data was passed in to perform reregistration
   * (like, say, an already registered and active entity)
   */
  public @ResponseBody ResponseEntity<Result<R>> reregister(@RequestBody R reregisteringEntity) throws DataStoreException;
}