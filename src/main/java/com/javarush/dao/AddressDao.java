package com.javarush.dao;

import com.javarush.domain.Address;
import org.hibernate.SessionFactory;

public class AddressDao extends LogicDao<Address> {
    public AddressDao(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
