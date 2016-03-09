package com.instant.service.favourites;

import com.instant.api.model.venue.Venue;
import com.instant.persistence.model.social.UserAccount;
import com.instant.persistence.model.venue.VenueEntity;
import com.instant.service.user.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sroshchupkin
 */

@Service
public class FavouritesServiceImpl implements FavouritesService{

    @Autowired
    UserAccountService userAccountService;

    UserAccount account;

    @Override
    public List<Venue> checkAndGetVenues(List<Venue> venues) {
        account=userAccountService.getCurrentUser();
        if(account!=null){
            for(int i = 0; i< venues.size(); i++){
                Venue venueEntity = venues.get(i);
                if(account.getFavouritesVenues().contains(venueEntity.getId())){
                    venueEntity.setFavourite(true);
                    venues.set(i, venueEntity);
                }
            }
        }
        return venues;
    }
}
