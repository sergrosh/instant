package com.instant.security;

import com.instant.persistence.model.security.SpringUser;
import com.instant.persistence.repository.user.SpringUserRepository;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author sroshchupkin
 */
public class SpringUserDetailsService implements UserDetailsService {
    protected Logger logger = Logger.getLogger(this.getClass());

    private SpringUserRepository springUserRepository;

    public SpringUserDetailsService(SpringUserRepository springUserRepository) {
        this.springUserRepository = springUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SpringUser user;
        if (username.contains("@")) {
            user = springUserRepository.findByEmail(username);
        } else {
            user = springUserRepository.findByUsername(username);
        }
        if (user == null) {
            logger.debug("Mongo returned no results for user '" + username + "'");
            throw new UsernameNotFoundException("Username '" + username + "' not found");
        } else {
            return user;
        }
    }
}

