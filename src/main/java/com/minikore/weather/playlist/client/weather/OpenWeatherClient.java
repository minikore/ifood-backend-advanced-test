package com.minikore.weather.playlist.client.weather;

import com.minikore.weather.playlist.client.weather.config.OpenWeatherRequestInterceptor;
import com.minikore.weather.playlist.client.weather.model.OpenWeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${openweather.name}", url = "${openweather.api.url}",
    configuration = OpenWeatherRequestInterceptor.class)
public interface OpenWeatherClient {

    @GetMapping
    OpenWeatherResponse search(@RequestParam(name = "q") String query);

    @GetMapping
    OpenWeatherResponse findByCoordinates(@RequestParam(name = "lat") Double latitude,
                                          @RequestParam(name = "lon") Double longitude);
}