//package com.ak.dao;
//
//import com.ak.entity.TeamStanding;
//import io.dropwizard.testing.junit.DAOTestRule;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//public class StandingDaoTest {
//
//    private StandingDao standingDao;
//
//    @Rule
//    public DAOTestRule database = DAOTestRule.newBuilder().addEntityClass(TeamStanding.class).build();
//
//    @Before
//    public void setUp() throws Exception {
//        standingDao = new StandingDao(database.getSessionFactory());
//    }
//
////    @Test
////    public void createTeamStanding() {
////        TeamStanding teamStanding = new TeamStanding();
//////        teamStanding.setTeamId(5);
////        TeamStanding addedData = database.inTransaction(() -> {
////            return standingDao.create(teamStanding);
////        });
////        assertThat(teamStanding.getId()).isEqualTo(addedData.getId());
////    }
//
//}
