package com.ak.bootstrap;


import com.ak.Resource.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.reflections.Reflections;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Set;

public class FootballApp extends Application<FootballConfig> {

    private HibernateBundle<FootballConfig> hibernateBundle;
    private GuiceBundle.Builder<FootballConfig> guiceBundle;

    public static void main(String[] args) throws Exception {
        new FootballApp().run(args);
    }

    @Override
    public void initialize(Bootstrap<FootballConfig> bootstrap) {
        getHibernateBundle();
        guiceBundle = GuiceBundle.newBuilder();
        guiceBundle.setConfigClass(FootballConfig.class);
        guiceBundle.addModule(new AppModule(hibernateBundle));
        guiceBundle.enableAutoConfig("com.ak");

        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(guiceBundle.build());
    }

    public void run(FootballConfig footballConfig, Environment environment) throws Exception {
        ArrayList<AppModule> modulesList = Lists.newArrayList(new AppModule(hibernateBundle));
        Injector injector = Guice.createInjector(modulesList);
        environment.jersey().register(injector.getInstance(StandingResource.class));
        environment.jersey().register(injector.getInstance(LeagueResource.class));
        environment.jersey().register(injector.getInstance(GameResource.class));
        environment.jersey().register(injector.getInstance(PlayerResource.class));
        environment.jersey().register(injector.getInstance(TeamResource.class));
        environment.jersey().register(injector.getInstance(StadiumResource.class));
    }

    private void getHibernateBundle() {

        if (hibernateBundle == null) {
            Reflections r = new Reflections("com.ak.entity");
            Set<Class<?>> hibernateEntityClasses = r.getTypesAnnotatedWith(Entity.class);
            hibernateBundle = new HibernateBundle<FootballConfig>(ImmutableList.copyOf(hibernateEntityClasses), new SessionFactoryFactory()) {
                @Override
                public DataSourceFactory getDataSourceFactory(FootballConfig appConfiguration) {
                    return appConfiguration.getDataSourceFactory();
                }
            };
        }
    }
}
