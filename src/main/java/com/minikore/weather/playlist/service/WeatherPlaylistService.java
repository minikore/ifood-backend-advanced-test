package com.minikore.weather.playlist.service;

import com.minikore.weather.playlist.controller.api.response.PlaylistResponse;
import reactor.core.publisher.Mono;

public interface WeatherPlaylistService {

    Mono<PlaylistResponse> getByCity(String city);

    Mono<PlaylistResponse> getByGeoCoordinates(Double lat, Double lon);

}
