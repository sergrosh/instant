package com.instant.security;

import com.instant.persistence.model.security.MongoRememberMeToken;
import com.instant.persistence.repository.rememberme.MongoRememberMeTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author sroshchupkin
 */
@Component
public class MongoPersistentTokenRepository implements PersistentTokenRepository {

    @Autowired
    private MongoRememberMeTokenRepository mongoRememberMeTokenRepository;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        MongoRememberMeToken mongoRememberMeToken = new MongoRememberMeToken(token.getUsername(), token.getSeries(),
                token.getTokenValue(), token.getDate());
        mongoRememberMeTokenRepository.save(mongoRememberMeToken);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        MongoRememberMeToken token = mongoRememberMeTokenRepository.findBySeries(series);
        token.setTokenValue(tokenValue);
        token.setDate(new Date());
        mongoRememberMeTokenRepository.save(token);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        try {
            MongoRememberMeToken mongoToken = mongoRememberMeTokenRepository.findBySeries(seriesId);
            return new PersistentRememberMeToken(mongoToken.getUsername(), mongoToken.getSeries(), mongoToken.getTokenValue(), mongoToken.getDate());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void removeUserTokens(String username) {
        mongoRememberMeTokenRepository.removeUserTokens(username);
    }
}
