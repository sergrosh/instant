package com.instant.common;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author sroshchupkin
 */
public abstract class LoggerBean {
    protected Logger logger = Logger.getLogger(this.getClass());

    protected void info(String message) {
        logger.info(message);
    }

    protected void debug(String message) {
        logger.debug(message);
    }

    protected void info(String message, Object... args) {
        logger.info(format(message, args));
    }

    protected void error(Exception e, String message, Object... args) {
        logger.error(format(message, args), e);
    }

    protected void error(String message, Object... args) {
        logger.error(format(message, args));
    }

    protected String format(String message, Object... args) {
        for (Object arg : args) {
            message = StringUtils.replaceOnce(message, "%s", arg.toString());
        }
        return message;
    }
}
