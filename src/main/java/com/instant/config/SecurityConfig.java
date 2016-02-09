package com.instant.config;

import com.instant.controller.Mappings;
import com.instant.persistence.model.social.UserRoleType;
import com.instant.persistence.repository.social.RememberMeTokenRepository;
import com.instant.persistence.repository.social.impl.MongoPersistentTokenRepositoryImpl;
import com.instant.service.UserAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import javax.inject.Inject;

/**
 * @author sroshchupkin
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

    @Inject
    private Environment environment;

    @Inject
    private UserAccountService userAccountService;

    @Inject
    private RememberMeTokenRepository rememberMeTokenRepository;

    @Inject
    private UsersConnectionRepository usersConnectionRepository;

    @Inject
    private SocialAuthenticationServiceLocator socialAuthenticationServiceLocator;

    @Inject
    private UserIdSource userIdSource;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/bower_components/**")
                .antMatchers("/fonts/**")
                .antMatchers("/images/**")
                .antMatchers("/scripts/**")
                .antMatchers("/styles/**")
                .antMatchers("/views/**")
                .antMatchers("/index.html")
                .antMatchers("/")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // disable CSRF now.
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasAuthority(UserRoleType.ROLE_ADMIN.name())
                .antMatchers("/company/**").hasAuthority(UserRoleType.ROLE_AUTHOR.name())
                .antMatchers("/user/**").authenticated()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/suggestions/**").permitAll()
                .antMatchers("/clients/**").permitAll()
                .antMatchers("/item/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/currentUser").permitAll()
                .antMatchers("/signin/**").permitAll()
                .antMatchers("/connect/**").permitAll()
                .antMatchers("/dist/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(socialAuthenticationFilter(), AbstractPreAuthenticatedProcessingFilter.class)
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutUrl("/signout")
                .logoutSuccessUrl("/")
                .and()
                .rememberMe()
                .rememberMeServices(rememberMeServices());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder
                .authenticationProvider(socialAuthenticationProvider())
                .authenticationProvider(rememberMeAuthenticationProvider())
                .userDetailsService(userAccountService);
    }

    /**
     * Must expose AuthenticationManager as bean.
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public SocialAuthenticationFilter socialAuthenticationFilter() throws Exception{
        SocialAuthenticationFilter filter = new SocialAuthenticationFilter(
                authenticationManager(), userIdSource,
                usersConnectionRepository, socialAuthenticationServiceLocator);
        filter.setFilterProcessesUrl("/signin");  //TODO fix the deprecated call.
        filter.setSignupUrl(null);
        filter.setConnectionAddedRedirectUrl(Mappings.USER);
        filter.setPostLoginUrl("/"); //always open account profile page after login
        filter.setRememberMeServices(rememberMeServices());
        return filter;
    }

    @Bean
    public SocialAuthenticationProvider socialAuthenticationProvider(){
        return new SocialAuthenticationProvider(usersConnectionRepository, userAccountService);
    }

    @Bean
    public RememberMeServices rememberMeServices(){
        PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices(
                environment.getProperty("application.key"), userAccountService, persistentTokenRepository());
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new MongoPersistentTokenRepositoryImpl(rememberMeTokenRepository);
    }

    @Bean
    public RememberMeAuthenticationProvider rememberMeAuthenticationProvider(){
        RememberMeAuthenticationProvider rememberMeAuthenticationProvider =
                new RememberMeAuthenticationProvider(environment.getProperty("application.key"));
        return rememberMeAuthenticationProvider;
    }

}