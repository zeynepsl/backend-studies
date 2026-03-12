package com.weatherapi.service;

import com.weatherapi.model.WeatherData;

public interface WeatherService {

    /**
     * Get current weather by city name
     * @param cityName name of the city
     * @return current weather data
     */
    WeatherData getCurrentWeatherByCity(String cityName);

    /**
     * Get current weather by city name and country code
     * @param cityName name of the city
     * @param countryCode ISO 3166 country code
     * @return current weather data
     */
    WeatherData getCurrentWeatherByCity(String cityName, String countryCode);

    /**
     * Get current weather by coordinates
     * @param latitude latitude
     * @param longitude longitude
     * @return current weather data
     */
    WeatherData getCurrentWeatherByCoordinates(Double latitude, Double longitude);
}
