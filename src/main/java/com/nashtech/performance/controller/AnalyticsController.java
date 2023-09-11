package com.nashtech.performance.controller;

import com.nashtech.performance.model.AnalyticsResponse;
import com.nashtech.performance.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling Google Analytics data retrieval.
 */
@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    /**
     * Endpoint for running a Google Analytics report.
     *
     * @param startDate The start date for the report (YYYY-MM-DD).
     * @param endDate   The end date for the report (YYYY-MM-DD).
     * @return A ResponseEntity containing the analytics data or an error message.
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/run-report")
    public ResponseEntity<?> runReport(
        @RequestParam String startDate,
        @RequestParam String endDate) {
        try {
            AnalyticsResponse response = analyticsService.sampleRunReport(startDate, endDate);

            if (response.getError() != null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            } else {
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            String errorMessage = "Error retrieving analytics data: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
