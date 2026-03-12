package com.weatherapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {

    private String cityName;
    private String country;
    private Double temperature;
    private Double feelsLike;
    private Double tempMin;
    private Double tempMax;
    private Integer humidity;
    private Integer pressure;
    private String weatherCondition;
    private String weatherDescription;
    private Double windSpeed;
    private Integer windDegrees;
    private Integer cloudiness;
    private Integer visibility;
    private Double latitude;
    private Double longitude;
    private Instant timestamp;
    private Instant sunrise;
    private Instant sunset;
}
