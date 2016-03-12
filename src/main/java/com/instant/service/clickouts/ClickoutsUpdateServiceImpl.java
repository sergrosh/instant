package com.instant.service.clickouts;

import com.instant.persistence.model.venue.VenueEntity;
import com.instant.persistence.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author sroshchupkin
 */
@Service
public class ClickoutsUpdateServiceImpl implements ClickoutsUpdateService {
    ConcurrentMap<String, MutableInt> clickoutMap = new ConcurrentHashMap<>();

    @Autowired
    VenueRepository venueRepository;


    @Override
    public int getClickoutMapSize() {
        return clickoutMap.size();
    }

    @Override
    public void updateClickoutMap(String venueId){
        MutableInt count = clickoutMap.get(venueId);
        if (count == null) {
            clickoutMap.put(venueId, new MutableInt());
        }
        else {
            count.increment();
        }
    }

    @Override
    public void updateEntity(){
        for(Map.Entry<String,MutableInt> clickoutEntry:clickoutMap.entrySet()){
            VenueEntity venue = venueRepository.findById(clickoutEntry.getKey());
            venue.setClickouts(venue.getClickouts()+clickoutEntry.getValue().get());
            venueRepository.save(venue);
            clickoutMap.clear();
        }
    }
}
