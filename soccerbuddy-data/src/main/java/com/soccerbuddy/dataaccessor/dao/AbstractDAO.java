package com.soccerbuddy.dataaccessor.dao;

import java.net.UnknownHostException;
import java.util.Properties;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.DatastoreImpl;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.soccerbuddy.dataaccessor.DataAccessorPreferences;

/**
 * An abstract {@code DAO} class that augments the abilities of the {@link BasicDAO}
 * by providing useful {@code final} utility methods.
 * 
 * @author manob2w
 * @author mystarrocks
 *
 * @param <T>  the type as needed by the {@link BasicDAO}
 * @param <K>  the type as needed by the {@link BasicDAO}
 */
public abstract class AbstractDAO<T, K> extends BasicDAO<T, K>{
  
  private static Datastore SOCCERBUDDY_DATASTORE;
  
  static {
    try {
      Properties preferences = DataAccessorPreferences.preferences();
      MongoClientURI uri = new MongoClientURI(preferences.getProperty("soccerbuddy.cloud.mongo.url"));
      MongoClient client = new MongoClient(uri);
      Morphia morphia = MorphiaBundle.morphiaBundle();
      SOCCERBUDDY_DATASTORE = new DatastoreImpl(morphia, client, "soccer-buddy");
    } catch (UnknownHostException e) {
      // react by logging for now; may enhance to retry initializing the data store in future versions
      e.printStackTrace();
    }
  }
  
  /**
   * Gets you a completely initialized data store to make database calls. 
   * 
   * @return a completely initialized data store to make database calls
   */
  protected static final Datastore dataStore() {
    return SOCCERBUDDY_DATASTORE;
  }

  /**
   * Instantiates this type given the entity clazz and the {@code Mongo data store}.
   * 
   * @param entityClass  the entity clazz
   * @param datastore  the {@code Mongo data store} to perform database operations
   */
  public AbstractDAO(Class<T> entityClass) {
    super(entityClass, SOCCERBUDDY_DATASTORE);
  }
}
