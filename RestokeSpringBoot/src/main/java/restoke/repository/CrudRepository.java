package restoke.repository;

public interface CrudRepository<T> {
    void save(T entity);
    void update(T entity);
    void delete(T entity);

}
