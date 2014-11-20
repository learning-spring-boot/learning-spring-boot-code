package learningspringboot;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final Logger log =
			LoggerFactory.getLogger(SecurityConfiguration.class);

	// tag::dev[]
	@Autowired
	public void configureForDevelopment(AuthenticationManagerBuilder auth,
		Environment env) throws Exception {
		if (env.acceptsProfiles("!production")) {
			log.info("Setting up memory-based authentication for dev");
			auth.inMemoryAuthentication()
				.withUser("phil").password("webb").roles("USER").and()
				.withUser("roy").password("clarkson").roles("USER", "ADMIN");
		}
	}
	// end::dev[]

	// tag::production[]
	@Autowired
	public void configureForProduction(AuthenticationManagerBuilder auth,
		DataSource dataSource, Environment env) throws Exception {
		if (env.acceptsProfiles("production")) {
			log.info("Setting up JDBC-based authentication for test database");
			auth.jdbcAuthentication().dataSource(dataSource);
		}
	}
	// end::production[]

	// tag::channel[]
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/teammates").permitAll()
				.anyRequest().authenticated()
				.and()
			.requiresChannel()
				.anyRequest().requiresSecure()
				.and()
			.formLogin()
				.defaultSuccessUrl("/teammates")
				.and()
			.logout()
				.logoutSuccessUrl("/teammates");
	}
	// end::channel[]
}
