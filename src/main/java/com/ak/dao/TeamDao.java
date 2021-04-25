package com.ak.dao;

import com.ak.entity.Team;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class TeamDao extends AbstractDAO<Team> implements HibernateEntityDao<Team> {

    @Inject
    public TeamDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Team create(Team entity) {
        return null;
    }

    @Override
    public List<Team> findAll() {
        Criteria criteria = currentSession().createCriteria(Team.class);
        return criteria.list();
    }

    @Override
    public Team findById(Integer id) {
        Criteria criteria = currentSession().createCriteria(Team.class);
        criteria.add(Restrictions.eq("id", id));
        return (Team) criteria.uniqueResult();
    }

    public Team findByName(String name) {
        Criteria criteria = currentSession().createCriteria(Team.class);
        criteria.add(Restrictions.eq("name", name));
        return (Team) criteria.uniqueResult();
    }

    @Override
    public void remove(Team team) {

    }
}
