package ee.tenman.exam.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class DataSourceConfiguration {

    private static final PostgreSQLContainer<?> POSTGRE_DB_CONTAINER = new PostgreSQLContainer<>("postgres:10-alpine");

    static {
        POSTGRE_DB_CONTAINER.start();
    }
    @Bean
    @Primary
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(POSTGRE_DB_CONTAINER.getJdbcUrl());
        dataSource.setUsername(POSTGRE_DB_CONTAINER.getUsername());
        dataSource.setPassword(POSTGRE_DB_CONTAINER.getPassword());
        return dataSource;
    }
}



