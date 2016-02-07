package com.instant.config;

import com.instant.persistence.model.social.UserAccount;
import com.instant.service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionSignUp;

import javax.inject.Inject;

/**
 * @author sroshchupkin
 */
public class AutoConnectionSignUp implements ConnectionSignUp{
    private static final Logger logger = LoggerFactory.getLogger(AutoConnectionSignUp.class);

    private final UserAccountService userAccountService;

    @Inject
    public AutoConnectionSignUp(UserAccountService userAccountService){
        this.userAccountService = userAccountService;
    }

    public String execute(Connection<?> connection) {
        ConnectionData data = connection.createData();
        UserAccount account = this.userAccountService.createUserAccount(data, connection.fetchUserProfile());

        if (logger.isDebugEnabled()) {
            logger.debug("Automatically create a new user account '"+account.getUserId()+"', for "+account.getDisplayName());
            logger.debug("connection data is from provider '"+data.getProviderId()+"', providerUserId is '"+data.getProviderUserId());
        }

        return account.getUserId();
    }
}

