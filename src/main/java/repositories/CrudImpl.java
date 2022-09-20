package repositories;

import domain.measurements.CarbonFootprint;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CrudImpl<T> implements CrudInterface<T> {

  private List<T> savedEntities;
  protected EntityManager em;
  protected T type;

  protected void withTransaction(Runnable block) {
    this.em.getTransaction().begin();
    try {
      block.run();
      this.em.getTransaction().commit();
    } catch (Exception e) {
      this.em.getTransaction().rollback();
    }
  }

  // --- Saving --- //

  private void _save_(T someEntity) {
    this.em.persist(someEntity);
  }

  @Override
  public void save(T someEntity) {
    this.withTransaction( () -> {
      this._save_(someEntity);
    });
  }

  @Override
  public void saveAll(T... someEntities) {
    this.withTransaction( () -> {
      Arrays
          .stream(someEntities)
          .iterator()
          .forEachRemaining(this::_save_);
    });
  }

  @Override
  public void saveAll(List<T> someEntities) {
    this.withTransaction( () -> {
      someEntities.forEach(this::_save_);
    });
  }

  // --- Queries --- //

  @Override
  public Long count() {
    Query q = this.em
        .createQuery("SELECT COUNT(t) FROM " + this.type.getClass().getSimpleName() + " t");
    return (Long) q.getResultList().get(0);
  }

  @Override
  public Boolean exists(T someEntity) {
    return this.em.getReference(CarbonFootprint.class, this.getId(someEntity)) != null;
  }

  @Override
  public T getByCondition(RepoCondition<T> someCondition) {
    return this.savedEntities
        .stream()
        .filter(someCondition::testConditionOn)
        .findFirst()
        .orElse(null);
  }

  @Override
  public List<T> getAll() {
    Query q = this.em
        .createQuery("SELECT t FROM " + this.type.getClass().getSimpleName() + " t", this.type.getClass());
    return (List<T>) q.getResultList();
  }

  // --- Deleting --- //

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

  protected void initEntityManager() {
    this.em = PerThreadEntityManagers.getEntityManager();
  }

  abstract Object getId(T someEntity);
}
