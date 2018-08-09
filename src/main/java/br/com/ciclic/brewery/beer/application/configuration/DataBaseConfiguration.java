package br.com.ciclic.brewery.beer.application.configuration;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
//@EnableJpaRepositories
public class DataBaseConfiguration {

    //@Value("{driver-class-name}")
    private String driver;
//    beer:
//    datasource:
//    jdbc-url: jdbc:postgres://stampy.db.elephantsql.com:5432/gkqdesjz
//    username: gkqdesjz
//    password: 0H60i2VQcIP3B5kXsyJvHGCQJkViiknd
//    maximum-pool-size: 30
//    driver-class-name: org.postgresql.Driver

   // @Bean
    //@Primary
    //@ConfigurationProperties(prefix="beer.datasource")
  //  public DataSource dataSource() {

//
//        return  DataSourceBuilder.create()
//                                .driverClassName("org.postgresql.Driver")
//                                .url("jdbc:postgres://stampy.db.elephantsql.com:5432/gkqdesjz")
//                                .username("gkqdesjz")
//                                .password("0H60i2VQcIP3B5kXsyJvHGCQJkViiknd")
//                                .build();
//    }
//
}
