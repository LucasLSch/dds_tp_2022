package repositories;

import org.hibernate.criterion.Criterion;

import java.util.List;

public interface CrudInterface<T> {

  Long count();

  void save(T someEntity);

  void saveAll(T... someEntity);

  void saveAll(List<T> someEntities);

  Boolean exists(T someEntity);

  T getById(Long id);

  List<T> getByCondition(Criterion someCriterion);

  void delete(T someEntity);

  void deleteByCondition(Criterion someCriterion);

  void deleteAll();

  void deleteAll(T... someEntity);

  void deleteAll(List<T> someEntities);

  List<T> getAll();

}
