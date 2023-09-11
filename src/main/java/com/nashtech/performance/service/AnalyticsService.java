package com.nashtech.performance.service;

import com.nashtech.performance.model.AnalyticsData;
import com.nashtech.performance.model.AnalyticsResponse;
import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.DateRange;
import com.google.analytics.data.v1beta.Metric;
import com.google.analytics.data.v1beta.Row;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service for retrieving Google Analytics data.
 */
@Service
public class AnalyticsService {

    private final Logger LOGGER = LoggerFactory.getLogger(AnalyticsService.class);

    @Value("${spring.cloud.gcp.project-id}")
    private String propertyId;

    /**
     * Runs a Google Analytics report for the specified property, start date, and end date.
     *
     * @param startDate The start date for the report (YYYY-MM-DD).
     * @param endDate   The end date for the report (YYYY-MM-DD).
     * @return An AnalyticsResponse containing the retrieved data or an error message.
     */
    public AnalyticsResponse sampleRunReport(String startDate, String endDate) {
        AnalyticsResponse analyticsResponse = new AnalyticsResponse();

        try (BetaAnalyticsDataClient analyticsData = BetaAnalyticsDataClient.create()) {
            RunReportRequest request = RunReportRequest.newBuilder()
                .setProperty("properties/" + this.propertyId)
                .addMetrics(Metric.newBuilder().setName("sessions"))
                .addMetrics(Metric.newBuilder().setName("averageSessionDuration"))
                .addMetrics(Metric.newBuilder().setName("totalUsers"))
                .addMetrics(Metric.newBuilder().setName("userEngagementDuration"))
                .addDateRanges(DateRange.newBuilder().setStartDate(startDate).setEndDate(endDate))
                .build();

            RunReportResponse response = analyticsData.runReport(request);

            for (Row row : response.getRowsList()) {
                AnalyticsData data = new AnalyticsData();
                data.setSessions(row.getMetricValues(0).getValue());
                data.setAverageSessionDuration(row.getMetricValues(1).getValue());
                data.setUsers(row.getMetricValues(2).getValue());
                data.setUserEngagement(row.getMetricValues(3).getValue());
                analyticsResponse.addData(data);
                LOGGER.info("Sessions: {}, Avg Session Duration: {}, Users: {}, User Engagement: {}",
                    data.getSessions(), data.getAverageSessionDuration(),
                    data.getUsers(), data.getUserEngagement());
            }
        } catch (Exception e) {
            LOGGER.error("Error retrieving analytics data: {}", e.getMessage());
            analyticsResponse.setError("Error retrieving analytics data: " + e.getMessage());
        }
        return analyticsResponse;
    }
}
