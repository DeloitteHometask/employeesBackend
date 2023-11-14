package employees.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class RolesConfigurationImpl implements RolesConfiguration{
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.OPTIONS).permitAll().
				requestMatchers("/websocket/company/**").permitAll()
				.requestMatchers(HttpMethod.GET).permitAll()
				.requestMatchers("/accounts/**").permitAll()
				.requestMatchers(HttpMethod.POST).authenticated()
				.anyRequest().hasRole("ADMIN"));
	}
}