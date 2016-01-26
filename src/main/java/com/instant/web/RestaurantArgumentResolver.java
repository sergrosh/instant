package com.instant.web;

import com.instant.persistence.model.Restaurant;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author sroshchupkin
 */

@Component
public class RestaurantArgumentResolver extends AbstractHandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Restaurant.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Restaurant restaurant = new Restaurant();
        restaurant.setId(getTrimmedParameter(webRequest, RESTAURANT_ID));
        restaurant.setName(getTrimmedParameter(webRequest, RESTAURANT_NAME));
        restaurant.setAddress(getTrimmedParameter(webRequest, RESTAURANT_ADDRESS));
        return restaurant;
    }
}
