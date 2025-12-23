package com.javarush.dao;

import com.javarush.domain.FilmText;
import org.hibernate.SessionFactory;

public class FilmTextDao extends LogicDao<FilmText> {
    public FilmTextDao(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
