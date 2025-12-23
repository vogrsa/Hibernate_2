package com.javarush.dao;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public abstract class LogicDao<T> {
    private final Class<T> clazz;

    private SessionFactory sessionFactory;
    public LogicDao(final Class<T> clazzToSet, SessionFactory sessionFactory ) {
        this.clazz = clazzToSet;
        this.sessionFactory = sessionFactory;
    }

    public T getById(final int id) {
        return (T) getCurrentSession().get(clazz, id);
    }
    public List<T> getItems(int offset, int count) {
        Query query = getCurrentSession().createQuery("from" + clazz.getName(), clazz);
        query.getFirstResult();
        query.setMaxResults(count);
        return query.getResultList();
    }
public List<T> findAll() {
        return getCurrentSession().createQuery("from" + clazz.getName(), clazz).list();
}
public T update(final T entity) {
    return (T) getCurrentSession().merge(entity);
}
public void delete(final T entity) {
    getCurrentSession().delete(entity);
}
public void deleteById(final int entityId) {
        final T entity = getById(entityId);
        delete(entity);
}
protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
}
}
