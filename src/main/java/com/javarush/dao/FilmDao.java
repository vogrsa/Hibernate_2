package com.javarush.dao;

import com.javarush.domain.Film;
import org.hibernate.SessionFactory;

public class FilmDao extends LogicDao<Film> {
    public FilmDao(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }
}
