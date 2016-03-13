package com.instant.component.task;

import com.instant.service.clickouts.ClickoutsUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author sroshchupkin
 */

@Component
public class ClickoutsUpdateTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClickoutsUpdateTask.class);

    @Autowired
    ClickoutsUpdateService clickoutsUpdateService;

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void updateClickouts() {
        if (clickoutsUpdateService.getClickoutMapSize() > 0) {
            clickoutsUpdateService.updateEntity();
            LOGGER.info("clickouts were updated in db and clickouts map was cleared");
        } else {
            LOGGER.info("clickouts map was empty");
        }
    }
}
