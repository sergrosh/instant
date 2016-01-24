package com.instant.web;

import com.instant.common.Constants;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;


/**
 * @author sroshchupkin
 */
public abstract class AbstractHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver, Constants {

    public String getTrimmedParameter(NativeWebRequest webRequest, String paramName) {
        String value = webRequest.getParameter(paramName);
        if (StringUtils.isEmpty(value)) {
            return null;
        } else {
            return value.trim();
        }
    }
}
