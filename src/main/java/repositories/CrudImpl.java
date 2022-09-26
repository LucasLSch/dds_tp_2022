package repositories;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public abstract class CrudImpl<T> implements CrudInterface<T> {

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
    return this.em.getReference(this.type.getClass(), this.getId(someEntity)) != null;
  }

  @Override
  public T getById(Long id) {
    return (T) this.em.getReference(this.type.getClass(), id);
  }

  @Override
  public List<T> getByCondition(Criterion someCriterion) {
    Session session = this.em.unwrap(Session.class);
    Criteria criteria = session.createCriteria(this.type.getClass());
    criteria.add(someCriterion);

    List<T> results = null;
    this.em.getTransaction().begin();
    try {
      results = (List<T>) criteria.list();
      this.em.getTransaction().commit();
    } catch (Exception e) {
      this.em.getTransaction().rollback();
    }
    return results;
  }

  @Override
  public List<T> getAll() {
    Query q = this.em
        .createQuery("SELECT t FROM " + this.type.getClass().getSimpleName() + " t", this.type.getClass());
    return (List<T>) q.getResultList();
  }

  // --- Deleting --- //

  private void _delete_(T someEntity) {
    if (this.exists(someEntity)) {
      this.em.remove(someEntity);
    }
  }

  @Override
  public void delete(T someEntity) {
    withTransaction(() -> this._delete_(someEntity));
  }

  @Override
  public void deleteByCondition(Criterion someCriterion) {
    Session session = this.em.unwrap(Session.class);
    Criteria criteria = session.createCriteria(this.type.getClass());
    criteria.add(someCriterion);
    withTransaction(() -> this.deleteAll((List<T>) criteria.list()));
  }

  @Override
  public void deleteAll() {
    withTransaction(() -> {
      Query q = this.em
          .createQuery("DELETE FROM " + this.type.getClass().getSimpleName());
    });
  }

  @Override
  public void deleteAll(T... someEntities) {
    withTransaction(() -> {
      Arrays
          .stream(someEntities)
          .iterator()
          .forEachRemaining(this::_delete_);
    });
  }

  @Override
  public void deleteAll(List<T> someEntities) {
    this.withTransaction( () -> {
      someEntities.forEach(this::_delete_);
    });
  }


  // --- Utils --- //

  protected void initEntityManager() {
    this.em = PerThreadEntityManagers.getEntityManager();
  }

  abstract Object getId(T someEntity);
}
