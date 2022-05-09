package com.package com.david.demo.demo.services;


import com.example.weatherapp.Models.WeatherInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    private final RestTemplate restTemplate;

    @Value(value = "${weather.api.key}")
    private String apiKey;

    @Autowired
    public WeatherService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public WeatherInput getWeatherForCity(String city) {
        final String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + apiKey;
        WeatherInput weatherInputFromWeatherApi = this.restTemplate.getForObject(url, WeatherInput.class);
        return weatherInputFromWeatherApi;
    }
}