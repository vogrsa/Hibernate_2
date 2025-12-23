package com.javarush.dao;

import com.javarush.domain.Customer;
import org.hibernate.SessionFactory;

public class CustomerDao extends LogicDao<Customer> {
    public CustomerDao(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
