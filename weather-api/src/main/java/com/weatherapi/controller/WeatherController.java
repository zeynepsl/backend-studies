package com.weatherapi.controller;

import com.weatherapi.model.WeatherData;
import com.weatherapi.service.WeatherService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
@Validated
public class WeatherController {

    private final WeatherService weatherService;

    /**
     * Get current weather by city name
     * Example: GET /api/v1/weather/current?city=Istanbul
     */
    @GetMapping("/current")
    public ResponseEntity<WeatherData> getCurrentWeatherByCity(
            @RequestParam @NotBlank(message = "City name cannot be empty") String city,
            @RequestParam(required = false) String country) {

        log.info("Received request for weather data - city: {}, country: {}", city, country);

        WeatherData weatherData = country != null
                ? weatherService.getCurrentWeatherByCity(city, country)
                : weatherService.getCurrentWeatherByCity(city);

        return ResponseEntity.ok(weatherData);
    }

    /**
     * Get current weather by city name (path variable)
     * Example: GET /api/v1/weather/current/Istanbul
     */
    @GetMapping("/current/{city}")
    public ResponseEntity<WeatherData> getCurrentWeatherByCityPath(
            @PathVariable @NotBlank(message = "City name cannot be empty") String city) {

        log.info("Received request for weather data - city: {}", city);

        WeatherData weatherData = weatherService.getCurrentWeatherByCity(city);
        return ResponseEntity.ok(weatherData);
    }

    /**
     * Get current weather by coordinates
     * Example: GET /api/v1/weather/coordinates?lat=41.0082&lon=28.9784
     */
    @GetMapping("/coordinates")
    public ResponseEntity<WeatherData> getCurrentWeatherByCoordinates(
            @RequestParam Double lat,
            @RequestParam Double lon) {

        log.info("Received request for weather data - latitude: {}, longitude: {}", lat, lon);

        WeatherData weatherData = weatherService.getCurrentWeatherByCoordinates(lat, lon);
        return ResponseEntity.ok(weatherData);
    }

    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Weather API is running!");
    }
}
