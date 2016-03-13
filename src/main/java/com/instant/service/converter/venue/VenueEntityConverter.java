package com.instant.service.converter.venue;


import com.instant.api.model.venue.Venue;
import com.instant.persistence.model.venue.VenueEntity;
import org.springframework.core.convert.converter.Converter;


/**
 * converter - VenueEntity -> Venue
 *
 * @author Sergii Roshchupkin
 */
public final class VenueEntityConverter implements Converter<VenueEntity, Venue> {

    @Override
    public Venue convert(VenueEntity entity) {

        Venue venue = new Venue();
        venue.setId(entity.getId());
        venue.setName(entity.getName());
        venue.setCountry(entity.getCountry());
        venue.setCity(entity.getCity());
        venue.setDescription(entity.getDescription());
        venue.setAddress(entity.getAddress());
        venue.setCategory(entity.getCategory());
        venue.setCompany(entity.getCompany());

        venue.setEmail(entity.getEmail());
        venue.setPhonePrimary(entity.getPhonePrimary());
        venue.setPhoneSecondary(entity.getPhoneSecondary());
        venue.setMobilePrimary(entity.getMobilePrimary());
        venue.setMobileSecondary(entity.getMobileSecondary());
        venue.setWhatsApp(entity.getWhatsApp());
        venue.setFacebookPage(entity.getFacebookPage());
        venue.setWebsite(entity.getWebsite());

        venue.setImagePath(entity.getImagePath());
        venue.setSpeciality(entity.getSpeciality());
        venue.setMenu(entity.getMenu());
        venue.setAvgPrice(entity.getAvgPrice());
        venue.setGallery(entity.getGallery());
//        venue.setOpenTimes(entity.getOpenTimes());
//        venue.setCloseTimes(entity.getCloseTimes());

        venue.setOpenHours(entity.getOpenHours());
        venue.setRating(entity.getRating());
        venue.setReviews(entity.getReviews());
        venue.setLikes(entity.getLikes());
        venue.setLocation(entity.getLocation());

        venue.setParkingDetails(entity.getParkingDetails());
        venue.setWifiAvailability(entity.getWifiAvailability());
        venue.setSmokingAvailability(entity.getSmokingAvailability());
        venue.setAccessForDisabled(entity.getAccessForDisabled());
        venue.setTakeAwayAvailability(entity.getTakeAwayAvailability());
        venue.setOutdoorEnvironment(entity.getOutdoorEnvironment());
        venue.setKidsPlayground(entity.getKidsPlayground());

        return venue;
    }

    public boolean isVenueOpen() {
        return true;
    }
}
