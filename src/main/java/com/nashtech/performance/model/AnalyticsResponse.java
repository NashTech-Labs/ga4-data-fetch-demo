package com.nashtech.performance.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the response from Google Analytics, including data and optional error information.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalyticsResponse {

    /**
     * List of analytics data.
     */
    private List<AnalyticsData> data;

    /**
     * Optional error message, null if there are no errors.
     */
    private String error;

    /**
     * Add analytics data to the response.
     *
     * @param analyticsData The analytics data to add.
     */
    public void addData(AnalyticsData analyticsData) {
        if (data == null) {
            data = new ArrayList<>();
        }
        data.add(analyticsData);
    }
}
