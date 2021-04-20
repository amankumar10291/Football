package com.ak.dao;

import java.util.List;

public interface HibernateEntityDao <HE extends HibernateEntity> {

    HE create(HE entity);

    List<HE> findAll();

    HE findById(Integer id);

    void remove(HE he);
}
