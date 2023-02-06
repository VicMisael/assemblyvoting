package com.example.assemblyvoting.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

@Configuration
@Slf4j
public class DatabaseConfiguration extends AbstractR2dbcConfiguration {


    @Value("${assemblyvoting.datasource.db.host}")
    String host;
    @Value("${assemblyvoting.datasource.db.port}")
    String port;
    @Value("${assemblyvoting.datasource.db.name}")
    String dbname;
    @Value("${assemblyvoting.datasource.db.username}")
    String username;
    @Value("${assemblyvoting.datasource.db.password}")
    String password;


    @Override
    @Bean
    @NonNull
    public ConnectionFactory connectionFactory() {
        log.info("Creating Database connection");
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration
                .builder()
                .applicationName("assembly_voting")
                .host(host)
                .port(Integer.parseInt(port))
                .database(dbname)
                .username(username)
                .password(password)
                .build()
        );
    }
}
