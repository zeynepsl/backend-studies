package com.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponseDto {

    @JsonProperty("coord")
    private Coordinates coordinates;

    @JsonProperty("weather")
    private List<WeatherInfo> weather;

    @JsonProperty("base")
    private String base;

    @JsonProperty("main")
    private MainWeatherData main;

    @JsonProperty("visibility")
    private Integer visibility;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("dt")
    private Long timestamp;

    @JsonProperty("sys")
    private SystemInfo sys;

    @JsonProperty("timezone")
    private Integer timezone;

    @JsonProperty("id")
    private Long cityId;

    @JsonProperty("name")
    private String cityName;

    @JsonProperty("cod")
    private Integer statusCode;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Coordinates {
        @JsonProperty("lon")
        private Double longitude;

        @JsonProperty("lat")
        private Double latitude;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherInfo {
        @JsonProperty("id")
        private Integer id;

        @JsonProperty("main")
        private String main;

        @JsonProperty("description")
        private String description;

        @JsonProperty("icon")
        private String icon;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MainWeatherData {
        @JsonProperty("temp")
        private Double temperature;

        @JsonProperty("feels_like")
        private Double feelsLike;

        @JsonProperty("temp_min")
        private Double tempMin;

        @JsonProperty("temp_max")
        private Double tempMax;

        @JsonProperty("pressure")
        private Integer pressure;

        @JsonProperty("humidity")
        private Integer humidity;

        @JsonProperty("sea_level")
        private Integer seaLevel;

        @JsonProperty("grnd_level")
        private Integer groundLevel;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Wind {
        @JsonProperty("speed")
        private Double speed;

        @JsonProperty("deg")
        private Integer degrees;

        @JsonProperty("gust")
        private Double gust;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Clouds {
        @JsonProperty("all")
        private Integer cloudiness;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SystemInfo {
        @JsonProperty("type")
        private Integer type;

        @JsonProperty("id")
        private Long id;

        @JsonProperty("country")
        private String country;

        @JsonProperty("sunrise")
        private Long sunrise;

        @JsonProperty("sunset")
        private Long sunset;
    }
}
