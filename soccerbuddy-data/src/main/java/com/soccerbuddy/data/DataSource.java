package com.soccerbuddy.data;

import java.util.Optional;

import com.googlecode.objectify.Key;

/**
 * The utility that helps query/persist data from/to the data store.
 * 
 * @param <E>  the model/entity object type
 * @param <K>  the entity key type
 * 
 * @author mystarrocks
 * @since 1.0
 */
public interface DataSource<E, K> {
  /**
   * Inserts a new entity into the data store.
   * 
   * <p>
   * Analogous to a {@code put()} operation.
   * 
   * @param e  the entity to be inserted into the data store
   * @return the inserted copy of the entity possibly with updated
   * properties indicating a successful insertion
   * @see <a href="https://github.com/objectify/objectify/wiki/Concepts#operations"></a>
   */
  public E insertNew(E e);
  
  /**
   * Deletes an existing entity from the data store.
   * 
   * <p>
   * Analogous to a {@code delete()} operation.
   * 
   * @param e  the entity to be removed from the data store
   * @see <a href="https://github.com/objectify/objectify/wiki/Concepts#operations"></a>
   */
  public void deleteExisting(E e);
  
  /**
   * Updates an existing entity in the data store.
   * 
   * <p>
   * Analogous to a {@code put()} operation.
   * 
   * @param e  the entity to be updated in the data store
   * @return the updated copy of the entity possibly with updated
   * properties indicating a successful update
   * @see <a href="https://github.com/objectify/objectify/wiki/Concepts#operations"></a>
   */
  public E updateExisting(E e);

  /**
   * Finds and returns an from the data store with the given key.
   * 
   * <p>
   * Analogous to a {@code query()} operation
   * 
   * @param key  the key to find the entity from the data store
   * @return an entity from the data store with the given key (or {@code null}
   * if one was not found) wrapped in an {@code Optional}
   * @see <a href="https://github.com/objectify/objectify/wiki/Concepts#operations"></a>
   */
  public Optional<E> findOne(Key<K> key);

  /**
   * Finds and returns an entity from the data store with the given key property.
   * 
   * @param keyProperty  the key property to find the entity from the data store
   * @return an entity from the data store with the given key property (or {@code null}
   * if one was not found) wrapped in an {@code Optional}
   * @see <a href="https://github.com/objectify/objectify/wiki/Concepts#operations"></a>
   */
  public Optional<E> findOne(K keyProperty);
}
