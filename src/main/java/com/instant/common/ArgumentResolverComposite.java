package com.instant.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.List;

/**
 * Created by Sergey on 1/25/2016.
 */
@Component
public class ArgumentResolverComposite implements HandlerMethodArgumentResolver {

    @Autowired
    private List<HandlerMethodArgumentResolver> argumentResolvers;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        for (HandlerMethodArgumentResolver argumentResolver : argumentResolvers) {
            if (argumentResolver.supportsParameter(parameter)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        for (HandlerMethodArgumentResolver argumentResolver : argumentResolvers) {
            if (argumentResolver.supportsParameter(parameter)) {
                return argumentResolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
            }
        }
        return null;
    }
}
