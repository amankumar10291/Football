package com.ak.bootstrap;

import com.ak.service.SService;
import com.ak.service.StandingService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

public class AppModule extends AbstractModule {

    private HibernateBundle<FootballConfig> hibernateBundle;

    public AppModule(HibernateBundle<FootballConfig> hibernateBundle) {
        this.hibernateBundle = hibernateBundle;
    }


    @Override
    protected void configure() {
        bind(SService.class).to(StandingService.class);
    }

    @Provides
    public SessionFactory sessionFactoryProvider2() {
        return hibernateBundle.getSessionFactory();
    }

}
