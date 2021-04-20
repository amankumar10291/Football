package com.ak.dao;

import com.ak.entity.League;
import com.ak.entity.Team;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class LeagueDao extends AbstractDAO<League> implements HibernateEntityDao<League> {

    @Inject
    public LeagueDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public League create(League entity) {
        return null;
    }

    @Override
    public List<League> findAll() {
        return null;
    }

    @Override
    public League findById(Integer id) {
        Criteria criteria = currentSession().createCriteria(League.class);
        criteria.add(Restrictions.eq("id", id));
        return (League) criteria.uniqueResult();
    }


    @Override
    public void remove(League league) {

    }

    public List<League> getLeaguesByCountry(int countryId) {
        Criteria criteria = currentSession().createCriteria(League.class);
        criteria.add(Restrictions.eq("countryId", countryId));
        return criteria.list();
    }
}
