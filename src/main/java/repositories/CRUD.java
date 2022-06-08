package repositories;

import java.util.List;

public interface CRUD<T> {

  public Integer count();

  public void save(T someEntity);

  public void saveAll(T ... someEntity);

  public void saveAll(List<T> someEntities);

  public Boolean exists(T someEntity);

  // public Object findByCondition(Condition);

  public void delete(T someEntity);

  // public void deleteByCondition(Condition someCondition);

  public void deleteAll();

  public void deleteAll(T ... someEntity);

  public List<T> getAll();

}
