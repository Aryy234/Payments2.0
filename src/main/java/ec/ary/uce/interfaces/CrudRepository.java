package ec.ary.uce.interfaces;

import java.util.List;

public interface CrudRepository<T> {

    List<T> list() throws Exception;
    T byId(Long id) throws Exception;
    void save(T t) throws Exception;
    void update(T t) throws Exception;
    void delete(Long id) throws Exception;
}
