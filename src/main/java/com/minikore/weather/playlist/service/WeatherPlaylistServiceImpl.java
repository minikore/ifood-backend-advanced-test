package com.minikore.weather.playlist.service;

import com.minikore.weather.playlist.client.weather.model.OpenWeatherResponse;
import com.minikore.weather.playlist.controller.api.response.PlaylistResponse;
import com.minikore.weather.playlist.client.weather.OpenWeatherClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 *
 * If temperature (celcius) is above 30 degrees, suggest tracks for party
 * In case temperature is between 15 and 30 degrees, suggest pop music tracks
 * If it's a bit chilly (between 10 and 14 degrees), suggest rock music tracks
 * Otherwise, if it's freezing outside, suggests classical music tracks
 *
 */

@Service
@AllArgsConstructor
public class WeatherPlaylistServiceImpl implements WeatherPlaylistService {

    OpenWeatherClient weatherClient;

    @Override
    public Mono<PlaylistResponse> getByCity(String city) {
        OpenWeatherResponse openWeatherResponse = weatherClient.search(city);
        return getPlaylist(openWeatherResponse);
    }

    @Override
    public Mono<PlaylistResponse> getByGeoCoordinates(Double lat, Double lon) {
        OpenWeatherResponse openWeatherResponse = weatherClient.findByCoordinates(lat, lon);
        return getPlaylist(openWeatherResponse);
    }

    private Mono<PlaylistResponse> getPlaylist(OpenWeatherResponse openWeatherResponse) {
        TrackGenres trackGenres = getTrackGenres(openWeatherResponse.getTemp());

        return Mono.just(PlaylistResponse.builder().temp(openWeatherResponse.getTemp()).build());
    }

    private TrackGenres getTrackGenres(Double temp) {
        if(temp>=30)
            return TrackGenres.PARTY;

        if (temp >= 15 && temp < 30)
            return TrackGenres.POP;

        if (temp >= 10 && temp < 15)
            return TrackGenres.ROCK;

        return TrackGenres.CLASSIC;
    }

    enum TrackGenres {
        PARTY,
        POP,
        ROCK,
        CLASSIC
    }
}
