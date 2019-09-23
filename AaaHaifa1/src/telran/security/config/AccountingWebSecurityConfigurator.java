package telran.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AccountingWebSecurityConfigurator {
@Bean
PasswordEncoder getPasswordEncoder() {
	return new BCryptPasswordEncoder();
}
}
