package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CrudImpl<T> implements CrudInterface<T> {

  private List<T> savedEntities;

  @Override
  public Integer count() {
    return this.savedEntities.size();
  }

  @Override
  public void save(T someEntity) {
    this.savedEntities.add(someEntity);
  }

  @Override
  public void saveAll(T... someEntity) {
    for (T entity : someEntity) {
      this.save(entity);
    }
  }

  @Override
  public void saveAll(List<T> someEntity) {
    for (T entity : someEntity) {
      this.save(entity);
    }
  }

  @Override
  public Boolean exists(T someEntity) {
    return this.savedEntities.contains(someEntity);
  }

  @Override
  public T findByCondition(RepoCondition<T> someCondition) {
    return this.savedEntities
        .stream()
        .filter(entity -> someCondition.testConditionOn(entity))
        .findFirst()
        .orElse(null);
  }

  @Override
  public void delete(T someEntity) {
    if (this.exists(someEntity)) {
      this.savedEntities.remove(someEntity);
    }
  }

  @Override
  public void deleteByCondition(RepoCondition<T> someCondition) {
    List<T> entitiesToDelete = this.savedEntities
        .stream()
        .filter(entity -> someCondition.testConditionOn(entity))
        .collect(Collectors.toList());
    this.deleteAll(entitiesToDelete);
  }

  @Override
  public void deleteAll() {
    this.savedEntities = new ArrayList<>();
  }

  @Override
  public void deleteAll(T... someEntity) {
    for (T entity : someEntity) {
      this.delete(entity);
    }
  }

  @Override
  public void deleteAll(List<T> someEntity) {
    for (T entity : someEntity) {
      this.delete(entity);
    }
  }

  @Override
  public List<T> getAll() {
    return this.savedEntities;
  }

  protected void initSavedEntities() {
    this.savedEntities = new ArrayList<>();
  }
}
