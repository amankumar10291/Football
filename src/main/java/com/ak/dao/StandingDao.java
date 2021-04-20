package com.ak.dao;

import com.ak.entity.Team;
import com.ak.entity.TeamStanding;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class StandingDao extends AbstractDAO<TeamStanding> implements HibernateEntityDao<TeamStanding> {

    @Inject
    public StandingDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public TeamStanding create(TeamStanding entity) {
        return null;
    }

    @Override
    public List<TeamStanding> findAll() {
        return null;
    }

    @Override
    public TeamStanding findById(Integer id) {
        return null;
    }

    @Override
    public void remove(TeamStanding teamStanding) {

    }

    public List<TeamStanding> findByTeamId(Integer teamId) {
        Criteria criteria = currentSession().createCriteria(TeamStanding.class);
        criteria.add(Restrictions.eq("teamId", teamId));
        return criteria.list();
    }

}
