package com.javarush.dao;

import com.javarush.domain.City;
import org.hibernate.SessionFactory;

public class CityDao extends LogicDao<City> {
    public CityDao(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
}
