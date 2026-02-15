import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class WeatherApp {

    public static void main(String[] args) {

        try {
            // API link to get current weather of Mumbai
            String weatherUrl =
                    "https://api.open-meteo.com/v1/forecast?latitude=19.07&longitude=72.87&current_weather=true";

            // Create HTTP client
            HttpClient httpClient = HttpClient.newHttpClient();

            // Build GET request
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(weatherUrl))
                    .GET()
                    .build();

            // Send request and store response
            HttpResponse<String> httpResponse =
                    httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Convert response body into JSON object
            JSONObject weatherData = new JSONObject(httpResponse.body());

            // Get current weather details
            JSONObject currentWeather = weatherData.getJSONObject("current_weather");

            double temperature = currentWeather.getDouble("temperature");
            double windSpeed = currentWeather.getDouble("windspeed");

            // Display formatted output
            System.out.println("Current Weather:");
            System.out.println("Temperature : " + temperature + " °C");
            System.out.println("Wind Speed  : " + windSpeed + " km/h");

        } catch (Exception error) {
            System.out.println("Something went wrong while fetching weather data.");
            error.printStackTrace();
        }
    }
}
