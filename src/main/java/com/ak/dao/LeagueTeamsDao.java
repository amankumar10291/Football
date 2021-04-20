package com.ak.dao;

import com.ak.entity.LeagueTeams;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Set;

public class LeagueTeamsDao extends AbstractDAO<LeagueTeams> implements HibernateEntityDao<LeagueTeams> {

    @Inject
    public LeagueTeamsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public LeagueTeams create(LeagueTeams entity) {
        return null;
    }

    @Override
    public List<LeagueTeams> findAll() {
        return null;
    }

    @Override
    public LeagueTeams findById(Integer id) {
        return null;
    }

    @Override
    public void remove(LeagueTeams leagueTeams) {

    }

    public List<LeagueTeams> getTeamsByLeague(int leagueId) {
        Criteria criteria = currentSession().createCriteria(LeagueTeams.class);
        criteria.add(Restrictions.eq("leagueId", leagueId));
        return criteria.list();
    }
}
