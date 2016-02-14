package com.instant.web;

import com.instant.persistence.model.Venue;
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
        return parameter.getParameterType().equals(Venue.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Venue venue = new Venue();
//        venue.setId(getTrimmedParameter(webRequest, VENUE_ID));
        venue.setName(getTrimmedParameter(webRequest, VENUE_NAME));
        venue.setCountry(getTrimmedParameter(webRequest, VENUE_COUNTRY));
        venue.setCity(getTrimmedParameter(webRequest, VENUE_CITY));
        venue.setCategory(getTrimmedParameter(webRequest, VENUE_CATEGORY));
        venue.setSpeciality(getTrimmedParameterList(webRequest, VENUE_SPECIALITIES));
        venue.setCompany(getTrimmedParameter(webRequest, VENUE_COMPANY));
        venue.setEmail(getTrimmedParameter(webRequest, VENUE_EMAIL));
        venue.setPhonePrimary(getTrimmedParameter(webRequest, VENUE_TELEPHONE_PRIMARY));
        venue.setPhoneSecondary(getTrimmedParameter(webRequest, VENUE_TELEPHONE_SECONDARY));
        venue.setMobilePrimary(getTrimmedParameter(webRequest, VENUE_MOBILE_PRIMARY));
        venue.setMobileSecondary(getTrimmedParameter(webRequest, VENUE_MOBILE_SECONDARY));
        venue.setFacebookPage(getTrimmedParameter(webRequest, VENUE_FACEBOOK_PAGE));
        venue.setWebsite(getTrimmedParameter(webRequest, VENUE_WEBSITE));
        venue.setAddress(getTrimmedParameter(webRequest, VENUE_ADDRESS));
        venue.setImagePath(getTrimmedParameter(webRequest, VENUE_IMAGE_PATH));
        venue.setDescription(getTrimmedParameter(webRequest, VENUE_DESCRIPTION));
        return venue;
    }

}
