package com.ak.bootstrap;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Data;

@Data
public class FootballConfig extends Configuration {

    @JsonProperty("database")
    private DataSourceFactory dataSourceFactory;
}
