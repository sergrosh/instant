package com.instant.web;

import com.instant.persistence.model.venue.VenueEntity;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author sroshchupkin
 */

@Component
public class VenueArgumentResolver extends AbstractHandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(VenueEntity.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        VenueEntity venueEntity = new VenueEntity();
//        venue.setId(getTrimmedParameter(webRequest, VENUE_ID));
        venueEntity.setName(getTrimmedParameter(webRequest, VENUE_NAME));
        venueEntity.setCountry(getTrimmedParameter(webRequest, VENUE_COUNTRY));
        venueEntity.setCity(getTrimmedParameter(webRequest, VENUE_CITY));
        venueEntity.setCategory(getTrimmedParameter(webRequest, VENUE_CATEGORY));
        venueEntity.setSpeciality(getTrimmedParameterList(webRequest, VENUE_SPECIALITIES));
        venueEntity.setCompany(getTrimmedParameter(webRequest, VENUE_COMPANY));
        venueEntity.setEmail(getTrimmedParameter(webRequest, VENUE_EMAIL));
        venueEntity.setPhonePrimary(getTrimmedParameter(webRequest, VENUE_TELEPHONE_PRIMARY));
        venueEntity.setPhoneSecondary(getTrimmedParameter(webRequest, VENUE_TELEPHONE_SECONDARY));
        venueEntity.setMobilePrimary(getTrimmedParameter(webRequest, VENUE_MOBILE_PRIMARY));
        venueEntity.setMobileSecondary(getTrimmedParameter(webRequest, VENUE_MOBILE_SECONDARY));
        venueEntity.setFacebookPage(getTrimmedParameter(webRequest, VENUE_FACEBOOK_PAGE));
        venueEntity.setWebsite(getTrimmedParameter(webRequest, VENUE_WEBSITE));
        venueEntity.setAddress(getTrimmedParameter(webRequest, VENUE_ADDRESS));
        venueEntity.setImagePath(getTrimmedParameter(webRequest, VENUE_IMAGE_PATH));
        venueEntity.setDescription(getTrimmedParameter(webRequest, VENUE_DESCRIPTION));
        return venueEntity;
    }

}
