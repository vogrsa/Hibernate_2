package com.javarush.dao;

import com.javarush.domain.Country;
import org.hibernate.SessionFactory;

public class CountryDao extends LogicDao<Country> {
    public CountryDao(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
