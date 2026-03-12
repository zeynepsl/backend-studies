package com.weatherapi.service;

import com.weatherapi.dto.WeatherResponseDto;
import com.weatherapi.model.WeatherData;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class WeatherMapper {

    public WeatherData toWeatherData(WeatherResponseDto dto) {
        if (dto == null) {
            return null;
        }

        return WeatherData.builder()
                .cityName(dto.getCityName())
                .country(dto.getSys() != null ? dto.getSys().getCountry() : null)
                .temperature(dto.getMain() != null ? dto.getMain().getTemperature() : null)
                .feelsLike(dto.getMain() != null ? dto.getMain().getFeelsLike() : null)
                .tempMin(dto.getMain() != null ? dto.getMain().getTempMin() : null)
                .tempMax(dto.getMain() != null ? dto.getMain().getTempMax() : null)
                .humidity(dto.getMain() != null ? dto.getMain().getHumidity() : null)
                .pressure(dto.getMain() != null ? dto.getMain().getPressure() : null)
                .weatherCondition(dto.getWeather() != null && !dto.getWeather().isEmpty()
                        ? dto.getWeather().get(0).getMain() : null)
                .weatherDescription(dto.getWeather() != null && !dto.getWeather().isEmpty()
                        ? dto.getWeather().get(0).getDescription() : null)
                .windSpeed(dto.getWind() != null ? dto.getWind().getSpeed() : null)
                .windDegrees(dto.getWind() != null ? dto.getWind().getDegrees() : null)
                .cloudiness(dto.getClouds() != null ? dto.getClouds().getCloudiness() : null)
                .visibility(dto.getVisibility())
                .latitude(dto.getCoordinates() != null ? dto.getCoordinates().getLatitude() : null)
                .longitude(dto.getCoordinates() != null ? dto.getCoordinates().getLongitude() : null)
                .timestamp(dto.getTimestamp() != null ? Instant.ofEpochSecond(dto.getTimestamp()) : null)
                .sunrise(dto.getSys() != null && dto.getSys().getSunrise() != null
                        ? Instant.ofEpochSecond(dto.getSys().getSunrise()) : null)
                .sunset(dto.getSys() != null && dto.getSys().getSunset() != null
                        ? Instant.ofEpochSecond(dto.getSys().getSunset()) : null)
                .build();
    }
}
