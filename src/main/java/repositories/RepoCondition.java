package repositories;

public interface RepoCondition<T> {

  Boolean testConditionOn(T someEntity);

}
