package com.soccerbuddy.dataaccessor.dao;

import java.util.List;

import org.mongodb.morphia.Key;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.UpdateResults;

import com.mongodb.WriteResult;
import com.soccerbuddy.dataaccessor.dao.request.MongoInfo;

/**
 * A contract for all the DAO created to implement the primary CRUD operations 
 * exposed in {@code BasicDAO}
 *
 *@author manob2w
 * @param <T> A class with type annotation {@link Entity}
 * @param <S> A class with fields annotated with {@link MongoInfo}
 */
public interface BaseDAO<T, S> {
  T find(S request);
  
  List<T> findAll(S request);
  
  List<Key<T>> createList(List<T> entities);
  
  UpdateResults update(S request);
  
  WriteResult deleteAllByMatch(S request);
  
  WriteResult delete(T entity);
}
