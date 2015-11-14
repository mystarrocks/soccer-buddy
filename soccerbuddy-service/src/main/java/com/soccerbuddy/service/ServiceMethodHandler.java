package com.soccerbuddy.service;

import static com.soccerbuddy.exception.LogMarker.TIMES;

import java.util.UUID;

import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.soccerbuddy.annotation.ServiceMethod;
import com.soccerbuddy.data.DataStoreException;
import com.soccerbuddy.exception.LogMarker;
import com.soccerbuddy.exception.ServiceError;
import com.soccerbuddy.exception.ServiceError.Type;
import com.soccerbuddy.model.Resource;
import com.soccerbuddy.model.ResponseCode;
import com.soccerbuddy.model.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * The {@code AOP} utility that point cuts the web service
 * methods and advises them.
 * 
 * <p>
 * Intercepts and advises all the methods annotated with {@code @ServiceMethod}.
 * Here's a definitive list of concerns the use of this annotation will help deal with:
 * <ul>
 *   <li>logging (logging of the method execution event with the result
 *   of execution)</li>
 *   <li>profiling (logging the start and end times of the methods to help measure
 *   the performance of the various method calls involved)</li>
 * </ul>
 * 
 * @author mystarrocks
 * @since 1.0
 * @see ServiceMethod
 */
@Aspect
@Component
@Slf4j
public class ServiceMethodHandler {
  
  /**
   * Intercepts and advises all the web service methods annotated with {@code @ServiceMethod}.
   * 
   * <p>
   * Here's a definitive list of concerns the use of this annotation will help deal with:
   * <ul>
   *   <li>logging (logging of the method execution event with the result 
   *   of execution)</li>
   *   <li>profiling (logging the start and end times of the methods to help measure 
   *   the performance of the various method calls involved)</li>
   * </ul>
   * 
   * @param joinPoint  the join point to proceed execution of target method
   * @param resource  the resource being acted on by the web service method
   * @param serviceMethod  the service method annotation with the advising preferences
   * @return the original response returned by the target method to the caller
   * @throws Throwable if the target method threw one
   */
  @SuppressWarnings("unchecked")
  @Around ("execution(public org.springframework.http.ResponseEntity *(..))"
      + "&& args(resource)"
      + "&& @annotation (serviceMethod)")
  public ResponseEntity<?> auditServiceMethod(ProceedingJoinPoint joinPoint, Resource resource, ServiceMethod serviceMethod) throws Throwable {
    ThreadContext.push(UUID.randomUUID().toString());
    
    LOGGER.debug("Intercepting method: {}", joinPoint.getSignature().getName());
    
    Logger targetLogger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
    Logger timingLogger = LoggerFactory.getLogger("timer");
    
    String action = serviceMethod.action();
    Marker failureMarker = LogMarker.asSlf4jMarker(serviceMethod.failureMarker());
    if (serviceMethod.customFailureMarker() != null && ! serviceMethod.customFailureMarker().equals("")) {
      failureMarker = LogMarker.asSlf4jMarker(serviceMethod.customFailureMarker());
    }
    
    long startTime = System.nanoTime();
    ResponseEntity<Result<?>> result = null;
    Exception exc = null;
    
    try {
      result = (ResponseEntity<Result<?>>) joinPoint.proceed(joinPoint.getArgs());
    } catch (DataStoreException e) {
      exc = e;
      targetLogger.error(failureMarker, action, exc);
      result = handleDataStoreException(resource, e);
    } catch (Exception e) {
      exc = e;
      targetLogger.error(failureMarker, action, exc);
    } finally {
      long endTime = System.nanoTime();
      logResult(resource, targetLogger, action, result, exc);
      
      long timeTaken = endTime - startTime;
      timingLogger.info(LogMarker.asSlf4jMarker(TIMES), Long.toString(timeTaken));
      
      resource.setAudited(true);
    }
    
    ThreadContext.pop();
    return result;
  }
  
  private ResponseEntity<Result<?>> handleDataStoreException(Resource resource, DataStoreException e) {
    ServiceError error = ServiceError
        .builder()
        .type(Type.DATA_ERROR)
        .description(e.getMessage())
        .build();
    Result<?> failure = Result
        .builder()
        .resource(resource)
        .error(error)
        .responseCode(ResponseCode.ERROR.code())
        .responseMessage(e.getRawMessage())
        .build();
    ResponseEntity<Result<?>> result = new ResponseEntity<Result<?>>(failure, HttpStatus.BAD_REQUEST);
    return result;
  }

  private void logResult(Resource resource, Logger targetLogger, String action, ResponseEntity<Result<?>> result, Exception exc) {
    if (result != null) {
      if (exc == null) {
        targetLogger.info("Action: {} | Resource: {} | Result: {} | Status: {} | Exception: {}", action, resource, result.getBody(), result.getStatusCode(), exc);
      } else {
        targetLogger.error("Action: {} | Resource: {} | Result: {} | Status: {} | Exception: {}", action, resource, result.getBody(), result.getStatusCode(), exc);
      }
    } else {
      targetLogger.error("Action: {} | Resource: {} | Result: {} | Status: {} | Exception: {}", action, resource, result, "500", exc);
    }
  }
}
