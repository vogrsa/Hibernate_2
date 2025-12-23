package com.javarush.dao;

import com.javarush.domain.Store;
import org.hibernate.SessionFactory;

public class StoreDao extends LogicDao<Store> {

    public StoreDao(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
