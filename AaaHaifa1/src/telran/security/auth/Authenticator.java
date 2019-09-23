package telran.security.auth;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import telran.security.services.IAuthService;
@Configuration
public class Authenticator implements UserDetailsService {
@Autowired
	IAuthService authService;
@Value("${password_period:100000}")
int passwordPeriod;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String hashCode=null;
		if((hashCode=authService.getPasswordHash(username))==null) {
			throw new UsernameNotFoundException("");
		}
		LocalDateTime activationDate = authService.getActivationDate(username);
		if(ChronoUnit.MINUTES.between(activationDate, LocalDateTime.now())>passwordPeriod)
			throw new UsernameNotFoundException("expiered password period");
		
		
		return new User(username, hashCode, AuthorityUtils.createAuthorityList("ROLE_USER"));
	}

}
