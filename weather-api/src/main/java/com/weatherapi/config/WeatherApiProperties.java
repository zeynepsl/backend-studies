package com.weatherapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "weather.api")
public class WeatherApiProperties {

    private String baseUrl = "https://api.openweathermap.org/data/2.5";
    private String apiKey;
    private String units = "metric"; // metric, imperial, standard
    private String language = "tr"; // tr, en, etc.
}
