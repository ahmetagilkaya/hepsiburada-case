package tr.com.hepsiburada.etl_app.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Value("${app.datasource.postgresql.url}")
    private String url;
    @Value("${app.datasource.postgresql.username}")
    private String username;
    @Value("${app.datasource.postgresql.password}")
    private String password;


    @Bean("postgresqlDataSourceProperties")
    public DataSourceProperties postgresqlDataSourceProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setDriverClassName("org.postgresql.Driver");
        dataSourceProperties.setUrl(url);
        dataSourceProperties.setUsername(username);
        dataSourceProperties.setPassword(password);
        return dataSourceProperties;
    }

    @Bean("postgresqlDataSource")
    public HikariDataSource postgresqlDataSource(DataSourceProperties postgresqlDataSourceProperties) {
        return postgresqlDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

}
