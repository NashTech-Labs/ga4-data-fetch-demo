# Google Analytics 4 (GA4) Data Fetching Service

This Spring Boot application allows you to fetch Google Analytics data using the Google Analytics Data API. It provides
an API endpoint to retrieve analytics data for a specified property and date range.

## Pre-requisites

Before you can use this application, make sure you have the following prerequisites in place:

- Java 17 or higher
- Maven
- Google Analytics Property ID
- Google Analytics Credentials: `credentials.json` file (Already included; update if necessary)

## Dependencies

This project relies on the following major dependencies:

| Dependency                       | Description                                                                          |
|----------------------------------|--------------------------------------------------------------------------------------|
| Spring Boot                      | Spring Boot is used for building and running the application.                        |
| Google Analytics Data API Client | The Google Analytics Data API Client is used to interact with Google Analytics data. |
| Lombok                           | Lombok is used for reducing boilerplate code in Java classes.                        |
| Maven                            | Maven is the build and project management tool used for this project.                |
| Spring Boot Starter Web          | Spring Boot Starter Web is used for developing web applications with Spring Boot.    |
| Spring Boot Starter Test         | Spring Boot Starter Test is used for testing Spring Boot applications.               |

## Getting Started

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/NashTech-Labs/ga4-data-fetch-demo
   ```

2. Open the project in your preferred IDE.

3. Set Environment Variables

   | Variable                           | Description                                              | Example Value      |
   |------------------------------------|----------------------------------------------------------|--------------------|
   | **GOOGLE_APPLICATION_CREDENTIALS** | Path to your Google Cloud service account key JSON file. | `credentials.json` |
   | **PROJECT_ID**                     | Your Google Cloud Project ID.                            | `405143103`        |

4. Build and run the application:

   ```bash
   mvn spring-boot:run
   ```

5. The application will start, and you can access the API endpoint to fetch google analytics 4 data.

## API Endpoint

- **GET:  /analytics/run-report**

  Fetch analytics data for a specified date range.

    - Request parameters:
        - `startDate` (required): Start Date.
        - `endDate` (required): End Date.

  Example request:

   ```
   GET http://localhost:8080/analytics/run-report?startDate=2023-09-05&endDate=2023-09-08
   ```

  Example response:

   ```json
   {
     "data": [
       {
         "sessions": "1234",
         "averageSessionDuration": "00:05:30",
         "logout": "50",
         "validated": "100"
       }
     ]
   }
   ```

## Error Handling

- If an error occurs while fetching analytics data, the API will return an error response.

  Example Response

    ```json
    {
    "error": "Error retrieving analytics data: io.grpc.StatusRuntimeException: INVALID_ARGUMENT: Invalid startDate : 0daysAg. startDate must be YYYY-MM-DD, NdaysAgo, yesterday, or today."
    }
    ```
