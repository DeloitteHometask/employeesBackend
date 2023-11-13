package telran.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.RequestMatcher;

import telran.spring.security.RolesConfiguration;

@Configuration
public class RolesConfigurationImpl implements RolesConfiguration{
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.OPTIONS).permitAll().
				requestMatchers("/websocket/employees/**").permitAll()
				.requestMatchers(HttpMethod.GET).authenticated()
				.anyRequest().hasRole("ADMIN"));
	}
}