package repositories;

import java.util.List;

public interface CrudInterface<T> {

  Long count();

  void save(T someEntity);

  void saveAll(T... someEntity);

  void saveAll(List<T> someEntities);

  Boolean exists(T someEntity);

  T getById(Long id);

  T getByCondition(RepoCondition<T> someCondition);

  void delete(T someEntity);

  void deleteByCondition(RepoCondition<T> someCondition);

  void deleteAll();

  void deleteAll(T... someEntity);

  void deleteAll(List<T> someEntities);

  List<T> getAll();

}
