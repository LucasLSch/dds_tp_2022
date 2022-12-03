package ddsutn.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public abstract class GenericSvcImpl<T, ID> {

  public abstract CrudRepository<T, ID> getRepo();

  public T save(T entity) {
    return this.getRepo().save(entity);
  }

  public Iterable<T> saveAll(List<T> entityList) {
    return this.getRepo().saveAll(entityList);
  }

  public T findById(ID id) {
    return this.getRepo().findById(id).orElse(null);
  }

  public List<T> findAll() {
    List<T> returnList = new ArrayList<>();
    this.getRepo().findAll().forEach(returnList::add);
    return returnList;
  }

  public List<T> findAllByCondition(Predicate<T> predicate) {
    List<T> returnList = this.findAll();
    return returnList.stream().filter(predicate).collect(Collectors.toList());
  }

  public void deleteById(ID id) {
    this.getRepo().deleteById(id);
  }

}
