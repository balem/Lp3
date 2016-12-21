package py.edu.utic.lp3.dao;

import java.util.List;

/**
 *
 * @author bala
 */
public interface GenericDAO<T> {
    public Boolean create(T t);
    public Boolean delete(Integer id);
    public Boolean update(T t);
    public T getByID(Integer id);
    public List<T> getAll();
}
