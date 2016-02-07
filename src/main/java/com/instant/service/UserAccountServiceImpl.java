package com.instant.service;

import com.instant.persistence.model.social.UserAccount;
import com.instant.persistence.model.social.UserRoleType;
import com.instant.persistence.repository.social.UserAccountRepository;
import com.instant.persistence.repository.system.CounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sroshchupkin
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    public static final String USER_ID_PREFIX = "user";

    private final UserAccountRepository accountRepository;
    private final CounterService  counterService;
    private final UserIdSource userIdSource = new AuthenticationNameUserIdSource();

    @Inject
    public UserAccountServiceImpl(UserAccountRepository accountRepository, CounterService counterService) {
        this.accountRepository = accountRepository;
        this.counterService = counterService;
    }

    @Override
    public UserAccount createUserAccount(ConnectionData data, UserProfile profile) {
        long userIdSequence = this.counterService.getNextUserIdSequence();

        UserRoleType[] roles = (userIdSequence == 1l) ?
                new UserRoleType[] { UserRoleType.ROLE_USER, UserRoleType.ROLE_AUTHOR, UserRoleType.ROLE_ADMIN } :
                new UserRoleType[] { UserRoleType.ROLE_USER };
        UserAccount account = new UserAccount(USER_ID_PREFIX + userIdSequence, roles);
        account.setEmail(profile.getEmail());
        account.setDisplayName(data.getDisplayName());
        account.setImageUrl(data.getImageUrl());
        if (userIdSequence == 1l) {
            account.setTrustedAccount(true);
        }
        LOGGER.info(String.format("A new user is created (userId='%s') for '%s' with email '%s'.", account.getUserId(),
                account.getDisplayName(), account.getEmail()));
        return this.accountRepository.save(account);
    }

    @Override
    public UserAccount addRole(String userId, UserRoleType role) throws UsernameNotFoundException {
        UserAccount account = loadUserByUserId(userId);
        Set<UserRoleType> roleSet = new HashSet<UserRoleType>();
        for (UserRoleType existingRole : account.getRoles()) {
            roleSet.add(existingRole);
        }
        roleSet.add(role);
        account.setRoles(roleSet.toArray(new UserRoleType[roleSet.size()]));
        return this.accountRepository.save(account);
    }

    @Override
    public UserAccount removeRole(String userId, UserRoleType role) throws UsernameNotFoundException {
        UserAccount account = loadUserByUserId(userId);
        Set<UserRoleType> roleSet = new HashSet<UserRoleType>();
        for (UserRoleType existingRole : account.getRoles()) {
            roleSet.add(existingRole);
        }
        roleSet.remove(role);
        account.setRoles(roleSet.toArray(new UserRoleType[roleSet.size()]));
        return this.accountRepository.save(account);
    }

    @Override
    public UserAccount loadUserByUserId(String userId) throws UsernameNotFoundException {
        UserAccount account = accountRepository.findByUserId(userId);
        if (account == null) {
            throw new UsernameNotFoundException("Cannot find user by userId " + userId);
        }
        return account;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByUserId(username);
    }

    @Override
    public UserAccount getCurrentUser() {
        return accountRepository.findByUserId(userIdSource.getUserId());
    }
}

