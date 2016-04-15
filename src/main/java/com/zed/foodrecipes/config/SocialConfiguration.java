package com.zed.foodrecipes.config;

import com.zed.foodrecipes.repository.UserRepository;
import com.zed.foodrecipes.repository.UserSocialConnectionRepository;
import com.zed.foodrecipes.signin.SimpleSignInAdapter;
import com.zed.social.mongo.repository.MongoUsersConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.connect.GoogleConnectionFactory;

/**
 * Created by Arnaud on 04/08/2015.
 */
@Configuration
@EnableSocial
public class SocialConfiguration extends SocialConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSocialConnectionRepository userSocialConnectionRepository;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {

        FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory(
                env.getProperty("facebook.clientId"),
                env.getProperty("facebook.clientSecret"));
        facebookConnectionFactory.setScope("email");
        cfConfig.addConnectionFactory(facebookConnectionFactory);

        GoogleConnectionFactory googleConnectionFactory = new GoogleConnectionFactory(
                env.getProperty("google.consumerKey"),
                env.getProperty("google.consumerSecret"));
        googleConnectionFactory.setScope("email");
        cfConfig.addConnectionFactory(googleConnectionFactory);
    }

    @Override
    @Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        MongoUsersConnectionRepository usersConnectionRepository = new MongoUsersConnectionRepository(userSocialConnectionRepository, connectionFactoryLocator, Encryptors.noOpText());
        //usersConnectionRepository.setConnectionSignUp(new UserConnectionSignUp(userRepository));
        return usersConnectionRepository;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Facebook facebook(ConnectionRepository repository) {
        Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
        return (connection != null) ? connection.getApi() : null;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Google google(ConnectionRepository repository) {
        Connection<Google> connection = repository.findPrimaryConnection(Google.class);
        return (connection != null) ? connection.getApi() : new GoogleTemplate();
    }

//    @Bean
//    public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository) {
//        ProviderSignInController controller = new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new SimpleSignInAdapter(new HttpSessionRequestCache()));
//        //controller.setSignUpUrl("/api/signup");
//        return controller;
//    }

//    @Bean
//    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository connectionRepository) {
//        return new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
//    }
}
