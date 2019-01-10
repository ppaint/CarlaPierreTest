package dao;

import java.util.List;

public interface DaoGeneric<T, K> {
	public List<T> findAll();

	public T findByKey(K key);

	public void insert(T obj);

	public void update(T obj);

	public void delete(T obj);

	public void deleteByKey(K key);

}
