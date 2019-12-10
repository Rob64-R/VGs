package com.example.games.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				// HTTP Basic authentication
				.httpBasic().and().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/registration").permitAll()
				.antMatchers(HttpMethod.GET, "/game/**").authenticated()
				.antMatchers(HttpMethod.POST, "/game").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT, "/game/**").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PATCH, "/game/**").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/game/**").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.POST, "/admin/**").hasAuthority("ADMIN")
				.and().csrf().disable().formLogin().disable();
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	
	
//	 @Autowired
//	    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//	    @Autowired
//	    private DataSource dataSource;
//
//	    @Value("${spring.queries.users-query}")
//	    private String usersQuery;
//
//	    @Value("${spring.queries.roles-query}")
//	    private String rolesQuery;
//
//	    @Override
//	    protected void configure(AuthenticationManagerBuilder auth)
//	            throws Exception {
//	        auth.
//	                jdbcAuthentication()
//	                .usersByUsernameQuery(usersQuery)
//	                .authoritiesByUsernameQuery(rolesQuery)
//	                .dataSource(dataSource)
//	                .passwordEncoder(bCryptPasswordEncoder);
//	    }
//
//	    @Override
//	    protected void configure(HttpSecurity http) throws Exception {
//
//	        http.
//	                authorizeRequests()
//	                .antMatchers("/").permitAll()
//	                .antMatchers("/login").permitAll()
//	                .antMatchers("/registration").permitAll()
//	                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
//	                .authenticated().and().csrf().disable().formLogin()
//	                .loginPage("/login").failureUrl("/login?error=true")
//	                .defaultSuccessUrl("/admin/home")
//	                .usernameParameter("email")
//	                .passwordParameter("password")
//	                .and().logout()
//	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	                .logoutSuccessUrl("/").and().exceptionHandling()
//	                .accessDeniedPage("/access-denied");
//	    }
//
//	    @Override
//	    public void configure(WebSecurity web) throws Exception {
//	        web
//	                .ignoring()
//	                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
//	    }

}