package gainmaster.service.user.measurements.persistence.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.bodymeasurements")
    public DataSource bodymeasurementsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oauthDataSource")
    @ConfigurationProperties(prefix="spring.datasource.oauth")
    public DataSource oauthDataSource() {
        return DataSourceBuilder.create().build();
    }
}
