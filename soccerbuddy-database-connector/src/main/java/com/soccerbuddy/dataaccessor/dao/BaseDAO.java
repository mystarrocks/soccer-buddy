package com.soccerbuddy.dataaccessor.dao;

import java.util.List;

import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.UpdateResults;

import com.mongodb.WriteResult;

/**
 * FIXME: add documentation.
 *
 * @param <T>
 * @param <S>
 */
public interface BaseDAO<T, S> {
  T find(S request);
  
  List<T> findAll(S request);
  
  List<Key<T>> createList(List<T> entities);
  
  UpdateResults update(S request);
  
  WriteResult deleteAllByMatch(S request);
  
  WriteResult delete(T entity);
}
