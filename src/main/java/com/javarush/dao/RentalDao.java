package com.javarush.dao;

import com.javarush.domain.Rental;
import org.hibernate.SessionFactory;

public class RentalDao extends LogicDao<Rental> {
    public RentalDao(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }
}
