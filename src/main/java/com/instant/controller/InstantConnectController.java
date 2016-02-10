package com.instant.controller;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;

import javax.inject.Inject;

/**
 * @author sroshchupkin
 */
public class InstantConnectController extends ConnectController {

    @Inject
    public InstantConnectController(ConnectionFactoryLocator connectionFactoryLocator,
                                    ConnectionRepository connectionRepository) {
        super(connectionFactoryLocator, connectionRepository);
    }

    protected String connectView(String providerId) {
        return "redirect:" + Mappings.USER;
    }

    protected String connectedView(String providerId) {
        return "redirect:" + Mappings.USER;
    }

}

