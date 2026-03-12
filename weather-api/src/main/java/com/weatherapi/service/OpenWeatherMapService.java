package com.weatherapi.service;

import com.weatherapi.config.WeatherApiProperties;
import com.weatherapi.dto.WeatherResponseDto;
import com.weatherapi.model.WeatherData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenWeatherMapService implements WeatherService {

    private final WebClient.Builder webClientBuilder;
    private final WeatherApiProperties properties;
    private final WeatherMapper weatherMapper;

    @Override
    public WeatherData getCurrentWeatherByCity(String cityName) {
        log.info("Fetching weather data for city: {}", cityName);

        String url = buildUrl("/weather")
                .queryParam("q", cityName)
                .build()
                .toUriString();

        return fetchWeatherData(url);
    }

    @Override
    public WeatherData getCurrentWeatherByCity(String cityName, String countryCode) {
        log.info("Fetching weather data for city: {}, country: {}", cityName, countryCode);

        String locationQuery = String.format("%s,%s", cityName, countryCode);
        String url = buildUrl("/weather")
                .queryParam("q", locationQuery)
                .build()
                .toUriString();

        return fetchWeatherData(url);
    }

    @Override
    public WeatherData getCurrentWeatherByCoordinates(Double latitude, Double longitude) {
        log.info("Fetching weather data for coordinates: lat={}, lon={}", latitude, longitude);

        String url = buildUrl("/weather")
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .build()
                .toUriString();

        return fetchWeatherData(url);
    }

    private WeatherData fetchWeatherData(String url) {
        try {
            WeatherResponseDto response = webClientBuilder.build()
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(WeatherResponseDto.class)
                    .block();

            if (response == null) {
                throw new RuntimeException("Failed to fetch weather data: Empty response");
            }

            return weatherMapper.toWeatherData(response);
        } catch (Exception e) {
            log.error("Error fetching weather data from URL: {}", url, e);
            throw new RuntimeException("Failed to fetch weather data: " + e.getMessage(), e);
        }
    }

    private UriComponentsBuilder buildUrl(String path) {
        return UriComponentsBuilder
                .fromHttpUrl(properties.getBaseUrl() + path)
                .queryParam("appid", properties.getApiKey())
                .queryParam("units", properties.getUnits())
                .queryParam("lang", properties.getLanguage());
    }
}
