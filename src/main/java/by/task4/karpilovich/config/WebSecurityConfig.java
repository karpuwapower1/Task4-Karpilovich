package by.task4.karpilovich.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import by.task4.karpilovich.handler.LoginSucsessHandler;
import by.task4.karpilovich.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String REGISTRATION_PATH = "/registration";
	private static final String EMPTY_PATH = "/";
	private static final String USERS_PATH = "/users";
	private static final String RESOURCES_PATH = "/resources/**";
	private static final String LOGIN_PATH = "/login";
	private static final int MAXIMUM_SESSIONS = -1;
	
	@Autowired
	UserService userService;
	@Autowired
	AuthenticationSuccessHandler loginHandler;
	
	@Bean
	public AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
		return new LoginSucsessHandler();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests()
			.antMatchers(REGISTRATION_PATH).not().fullyAuthenticated()
			.antMatchers(EMPTY_PATH, USERS_PATH).hasRole("USER")
			.antMatchers(RESOURCES_PATH).permitAll()
			.anyRequest().authenticated().and()
			.formLogin().loginPage(LOGIN_PATH).successHandler(loginHandler).permitAll()
			.and().logout().permitAll().logoutSuccessUrl(LOGIN_PATH);
		httpSecurity.sessionManagement().maximumSessions(MAXIMUM_SESSIONS).sessionRegistry(sessionRegistry()).expiredUrl(LOGIN_PATH);
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
	}
}