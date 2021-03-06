package gainmaster.service.user.measurements.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuthConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    @Value("#{oauthDataSource}")
    DataSource oauthDatasource;

    @Override
    public void configure(ResourceServerSecurityConfigurer oauthProvider) throws Exception {
        oauthProvider.resourceId("gainmaster").tokenServices(resourceTokenService());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //Allow OPTIONS preflight request
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/users/*/measurements").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/users/*/measurements/*").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    public ResourceServerTokenServices resourceTokenService() {
        DefaultTokenServices tokenService = new DefaultTokenServices();
        tokenService.setTokenStore(tokenStore());
        return tokenService;
    }

    @Bean
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(oauthDatasource);
    }

}
