package com.soccerbuddy.model;

/**
 * An auditable entity that can be part of loggers and
 * other auditing needs.
 * 
 * @author mystarrocks
 * @since 1.0
 */
public interface Auditable {
  
  /**
   * Sets the {@code audited} indicator on this entity to indicate
   * the completion of auditing.
   * 
   * @param audited  indicator on this entity to indicate
   * the completion of auditing; usually, this is {@code true}
   */
  public void setAudited(boolean audited);
  
  /**
   * Returns an indicator as to whether the entity has been audited.
   * 
   * @return {@code true} if the entity has been audited; {@code false}
   * otherwise
   */
  public boolean isAudited();
}
