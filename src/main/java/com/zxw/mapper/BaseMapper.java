package com.zxw.mapper;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface BaseMapper<T> {
    public void save(T t);

    public void update(T t);

    public void delete(T t);

    public void saveOrUpdate(T t);

    public T findById(Serializable id);

    public List<T> findAll();

    public void executeUpdate(String queryName, Object... objects);

    public List<T> findByCriteria(DetachedCriteria detachedCriteria);
}
