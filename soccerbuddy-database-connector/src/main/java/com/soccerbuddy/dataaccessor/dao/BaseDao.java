package com.soccerbuddy.dataaccessor.dao;

/**
 * FIXME: add documentation.
 *
 * @param <T>
 * @param <S>
 */
public interface BaseDao<T, S> {
  T find(S request);
}
