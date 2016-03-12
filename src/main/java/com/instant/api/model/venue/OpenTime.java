package com.instant.api.model.venue;

import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;

/**
 * @author sroshchupkin
 */

@Data
public class OpenTime {
    private DayOfWeek dayOfWeek;
    List<String> openIntervals;
}


