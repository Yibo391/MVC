package model.persistence;

import model.domain.AllData;

/**
 * data persistence, will be replaced by database.
 */
public interface DataPersistence {
  public AllData load();
  
  public void save(AllData reg);
}
