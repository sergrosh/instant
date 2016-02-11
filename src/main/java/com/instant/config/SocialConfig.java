package com.instant.config;

import com.instant.persistence.repository.social.UserSocialConnectionRepository;
import com.instant.persistence.repository.social.impl.MongoUsersConnectionRepositoryImpl;
import com.instant.service.user.UserAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialAuthenticationServiceLocator;

import javax.inject.Inject;

/**
 * @author sroshchupkin
 */
@Configuration
public class SocialConfig extends SocialConfigurerAdapter {
    @Inject
    private UserSocialConnectionRepository userSocialConnectionRepository;

    @Inject
    private UserAccountService userAccountService;


    @Bean
    public ConnectionSignUp autoConnectionSignUp() {
        return new AutoConnectionSignUp(userAccountService);
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        MongoUsersConnectionRepositoryImpl repository = new MongoUsersConnectionRepositoryImpl(
                userSocialConnectionRepository, (SocialAuthenticationServiceLocator) connectionFactoryLocator, Encryptors.noOpText());
        repository.setConnectionSignUp(autoConnectionSignUp());
        return repository;
    }

//    @Bean
//    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
//    public Google google(ConnectionRepository repository) {
//        Connection<Google> connection = repository.findPrimaryConnection(Google.class);
//        return connection != null ? connection.getApi() : new GoogleTemplate();
//    }

//    @Bean
//    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
//    public Facebook facebook(ConnectionRepository repository) {
//
//        Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
//        ConnectionData data = connection.createData();
//        return connection != null ? connection.getApi() : new FacebookTemplate(data.getAccessToken());
//    }

//    @Override
//    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
//        configurer.addConnectionFactory(new FacebookConnectionFactory(
//                environment.getProperty("spring.social.facebook.appId"), environment.getProperty("spring.social.facebook.appSecret")));
//    }
}

