package repositories;

import java.util.ArrayList;
import java.util.Arrays;
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
  public void saveAll(T... someEntities) {
    Arrays
        .stream(someEntities)
        .iterator()
        .forEachRemaining(this::save);
  }

  @Override
  public void saveAll(List<T> someEntities) {
    someEntities.forEach(this::save);
  }

  @Override
  public Boolean exists(T someEntity) {
    return this.savedEntities.contains(someEntity);
  }

  @Override
  public T findByCondition(RepoCondition<T> someCondition) {
    return this.savedEntities
        .stream()
        .filter(someCondition::testConditionOn)
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
        .filter(someCondition::testConditionOn)
        .collect(Collectors.toList());
    this.deleteAll(entitiesToDelete);
  }

  @Override
  public void deleteAll() {
    this.savedEntities = new ArrayList<>();
  }

  @Override
  public void deleteAll(T... someEntities) {
    Arrays
        .stream(someEntities)
        .iterator()
        .forEachRemaining(this::delete);
  }

  @Override
  public void deleteAll(List<T> someEntities) {
    someEntities.forEach(this::delete);
  }

  @Override
  public List<T> getAll() {
    return this.savedEntities;
  }

  protected void initSavedEntities() {
    this.savedEntities = new ArrayList<>();
  }
}
