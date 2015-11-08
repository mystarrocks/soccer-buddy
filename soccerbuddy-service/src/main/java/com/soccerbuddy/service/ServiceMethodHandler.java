package com.soccerbuddy.service;

import static com.soccerbuddy.model.LogMarker.TIMES;

import java.util.UUID;

import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.soccerbuddy.annotation.ServiceMethod;
import com.soccerbuddy.model.LogMarker;
import com.soccerbuddy.model.Resource;

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
   * @throws Throwable if the target method threw one
   */
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
    ResponseEntity<?> result = null;
    Exception exc = null;
    
    try {
      result = (ResponseEntity<?>) joinPoint.proceed(joinPoint.getArgs());
    } catch (Exception e) {
      exc = e;
      targetLogger.error(failureMarker, action, exc);
    } finally {
      long endTime = System.nanoTime();
      if (result != null) {
        targetLogger.info("Action: {} | Resource: {} | Result: {} | Status: {} | Exception: {}", action, resource, result.getBody(), result.getStatusCode(), exc);
      } else {
        targetLogger.info("Action: {} | Resource: {} | Result: {} | Status: {} | Exception: {}", action, resource, result, "500", exc);
      }
      
      long timeTaken = endTime - startTime;
      timingLogger.info(LogMarker.asSlf4jMarker(TIMES), Long.toString(timeTaken));
      
      resource.setAudited(true);
    }
    
    ThreadContext.pop();
    return result;
  }
}
