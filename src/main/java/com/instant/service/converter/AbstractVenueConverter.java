package com.instant.service.converter;


import com.instant.api.model.venue.NewVenue;
import com.instant.persistence.model.venue.VenueEntity;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Sergii Roshchupkin
 */
public abstract class AbstractVenueConverter<T extends NewVenue> implements Converter<T, VenueEntity> {

    @Override
    public VenueEntity convert(T newVenue) {

        VenueEntity entity = new VenueEntity();
        entity.setName(newVenue.getName());
        entity.setCountry(newVenue.getCountry());
        entity.setCity(newVenue.getCity());
        entity.setDescription(newVenue.getDescription());
        entity.setAddress(newVenue.getAddress());
        entity.setCategory(newVenue.getCategory());
        entity.setCompany(newVenue.getCompany());
        entity.setEmail(newVenue.getEmail());
        entity.setPhonePrimary(newVenue.getPhonePrimary());
        entity.setPhoneSecondary(newVenue.getPhoneSecondary());
        entity.setMobilePrimary(newVenue.getMobilePrimary());
        entity.setMobileSecondary(newVenue.getMobileSecondary());
        entity.setFacebookPage(newVenue.getFacebookPage());
        entity.setWebsite(newVenue.getWebsite());
        entity.setImagePath(newVenue.getImagePath());
        entity.setSpeciality(newVenue.getSpeciality());
        entity.setMenu(newVenue.getMenu());
        entity.setGallery(newVenue.getGallery());
        entity.setOpenTime(newVenue.getOpenTime());
        entity.setCloseTime(newVenue.getCloseTime());

        return entity;
    }


}
