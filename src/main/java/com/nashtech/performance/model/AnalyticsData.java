package com.nashtech.performance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyticsData {
    private String sessions;
    private String averageSessionDuration;
    private String users;
    private String userEngagement;
}