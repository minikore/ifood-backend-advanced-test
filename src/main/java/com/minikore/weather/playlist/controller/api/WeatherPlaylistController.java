package com.minikore.weather.playlist.controller.api;

import com.minikore.weather.playlist.controller.api.request.CityNameParamRequest;
import com.minikore.weather.playlist.controller.api.request.GeoCoordinatesParamRequest;
import com.minikore.weather.playlist.controller.api.response.PlaylistResponse;
import com.minikore.weather.playlist.service.WeatherPlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(("/weather-playlist"))
@AllArgsConstructor
public class WeatherPlaylistController {

    WeatherPlaylistService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private Mono<PlaylistResponse> getByCity(@Valid @ModelAttribute CityNameParamRequest params) {
        return service.getByCity(params.getCity());
    }

    @GetMapping(value = "coordinates", produces = MediaType.APPLICATION_JSON_VALUE)
    private Mono<PlaylistResponse> getByGeoCoordinates(@Valid @ModelAttribute GeoCoordinatesParamRequest params) {
        return service.getByGeoCoordinates(params.getLat(), params.getLon());
    }
}
