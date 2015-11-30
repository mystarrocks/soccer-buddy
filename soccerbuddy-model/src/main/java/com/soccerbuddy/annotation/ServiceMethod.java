package com.soccerbuddy.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.soccerbuddy.util.LogMarker;

/**
 * A {@code RESTful} web service operation/method that usually
 * accepts a {@code CRUD} resource and produces a result based
 * on the {@code HTTP} method attempted.
 * 
 * <p>
 * This annotation is intended to be used as a marker for {@code AOP}
 * classes to intercept methods that contain them and deal with some
 * of the common cross-cutting concerns like logging and profiling. However, 
 * too much of magic can lead to poor readability and maintainability, so the
 * annotation only supports what's really necessary to be {@code AOP}'ed
 * and the {@code AOP} code is required to adhere to this.
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
 * @author mystarrocks
 * @since 1.0
 */
@Target ({METHOD})
@Retention (RUNTIME)
@Documented
public @interface ServiceMethod {
  
  /**
   * The action this web service operation performs.
   * 
   * <p>
   * Defaults to "".
   * 
   * @return the action this web service operation performs
   */
  String action() default "";
  
  /**
   * The log marker to use in case of a failure.
   * 
   * <p>
   * Mutually exclusive with {@link #customFailureMarker()}. If both are provided,
   * {@code #customFailureMarker()} overrides this setting.
   * 
   * @return the log marker to use in case of a failure
   */
  LogMarker failureMarker() default LogMarker.UNKNOWN_ERROR;
  
  /**
   * The custom log marker name to use in case of a failure.
   * 
   * <p>
   * Mutually exclusive with {@link #failureMarker()}. If both are provided,
   * this overrides the marker provided by {@code #failureMarker()}. Defaults to 
   * {@code "UNKNOWN_ERROR"} if neither was provided.
   * 
   * @return the log marker name to use in case of a failure
   */
  String customFailureMarker() default "";
}
