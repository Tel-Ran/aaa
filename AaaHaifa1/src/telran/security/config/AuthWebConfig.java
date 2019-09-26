package telran.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
@Configuration
public class AuthWebConfig extends WebSecurityConfigurerAdapter {
	@Value("${authentication:enabled}")
	String authentication;
@Override
protected void configure(HttpSecurity http) throws Exception {
	http.httpBasic();
	http.csrf().disable();
	 http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	if(authentication.equals("enabled")) {
		http.authorizeRequests().anyRequest().authenticated();
		
	} else {
		http.authorizeRequests().anyRequest().permitAll();
	}
	
	
}
}
