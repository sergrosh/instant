package com.instant.config;

import com.instant.controller.Mappings;
import com.instant.persistence.model.social.UserRoleType;
import com.instant.persistence.repository.social.RememberMeTokenRepository;
import com.instant.persistence.repository.social.impl.MongoPersistentTokenRepositoryImpl;
import com.instant.service.user.UserAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
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

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

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
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority(UserRoleType.ROLE_ADMIN.name())
                .antMatchers("/company/**").hasAuthority(UserRoleType.ROLE_AUTHOR.name())
                .antMatchers(Mappings.UPLOAD_VENUE_IMAGE).hasAuthority(UserRoleType.ROLE_AUTHOR.name())
                .antMatchers(Mappings.DELETE_VENUE_IMAGE).hasAuthority(UserRoleType.ROLE_AUTHOR.name())
                .antMatchers(Mappings.CLIENTS+"/**").permitAll()
                .antMatchers(Mappings.REST_CLIENTS+"/**").permitAll()
                .antMatchers(Mappings.REST_VENUE+"/**").permitAll()
                .antMatchers(Mappings.USER+"/**").authenticated()
                .antMatchers(Mappings.ITEM+"/**").permitAll()
                .antMatchers("/home/**").permitAll()
                .antMatchers("/suggestions/**").permitAll()
                .antMatchers("/rest/item/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/signin/**").permitAll()
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
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
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
    public SocialAuthenticationFilter socialAuthenticationFilter() throws Exception {
        SocialAuthenticationFilter filter = new SocialAuthenticationFilter(
                authenticationManager(), userIdSource,
                usersConnectionRepository, socialAuthenticationServiceLocator);
        filter.setFilterProcessesUrl("/signin");
        filter.setSignupUrl(null);
        filter.setConnectionAddedRedirectUrl(Mappings.USER);
        filter.setPostLoginUrl("/"); //always open account profile page after login
        filter.setRememberMeServices(rememberMeServices());
        return filter;
    }

    @Bean
    public SocialAuthenticationProvider socialAuthenticationProvider() {
        return new SocialAuthenticationProvider(usersConnectionRepository, userAccountService);
    }

    @Bean
    public RememberMeServices rememberMeServices() {
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
    public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
        RememberMeAuthenticationProvider rememberMeAuthenticationProvider =
                new RememberMeAuthenticationProvider(environment.getProperty("application.key"));
        return rememberMeAuthenticationProvider;
    }

}