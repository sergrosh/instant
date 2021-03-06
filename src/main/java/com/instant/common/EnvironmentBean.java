package com.instant.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author sroshchupkin
 */

@Component
public class EnvironmentBean {
    @Autowired
    Environment environment;

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public String get(String key) {
        return environment.getProperty(key);
    }

    public Integer getPageSize() {
        return getInt("pagination.page.size");
    }

    public int getInt(String key) {
        return Integer.parseInt(environment.getProperty(key));
    }

}
