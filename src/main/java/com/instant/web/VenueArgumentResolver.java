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
        venue.setId(getTrimmedParameter(webRequest, VENUE_ID));
        venue.setName(getTrimmedParameter(webRequest, VENUE_NAME));
        venue.setType(getTrimmedParameter(webRequest, VENUE_TYPE));
        venue.setAddress(getTrimmedParameter(webRequest, VENUE_ADDRESS));
        venue.setImagePath(getTrimmedParameter(webRequest, VENUE_IMAGE_PATH));
        venue.setDescription(getTrimmedParameter(webRequest, VENUE_DESCRIPTION));
        return venue;
    }

}
