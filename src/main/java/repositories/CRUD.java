package repositories;

import java.util.List;

public interface CRUD<T> {

  public Integer count();

  public void save(T someEntity);

  public void saveAll(T ... someEntity);

  public void saveAll(List<T> someEntities);

  public Boolean exists(T someEntity);

  public T findByCondition(RepoCondition<T> someCondition);

  public void delete(T someEntity);

  public void deleteByCondition(RepoCondition<T> someCondition);

  public void deleteAll();

  public void deleteAll(T ... someEntity);

  public void deleteAll(List<T> someEntities)

  public List<T> getAll();

}
