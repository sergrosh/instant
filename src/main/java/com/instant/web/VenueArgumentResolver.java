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
        venue.setId(getTrimmedParameter(webRequest, RESTAURANT_ID));
        venue.setName(getTrimmedParameter(webRequest, RESTAURANT_NAME));
        venue.setAddress(getTrimmedParameter(webRequest, RESTAURANT_ADDRESS));
        return venue;
    }
}
