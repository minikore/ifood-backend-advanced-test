package com.minikore.weather.playlist.controllers;

import com.minikore.weather.playlist.model.Playlist;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.*;

@RestController
@RequestMapping(("/weather-playlist"))
public class WeatherPlaylistController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private Mono<Playlist> getByCity(@Valid @NotBlank @RequestParam String city) {
        return Mono.just(Playlist.builder().song("MiniKore").build());
    }

    @GetMapping(value = "coordinates", produces = MediaType.APPLICATION_JSON_VALUE)
    private Mono<Playlist> getByCoordinates(
        @Validated @RequestParam @NotNull @Min(-90) @Max(90) Double lat,
        @Valid @RequestParam @NotNull @Min(-180) @Max(180) Double lon) {
        return Mono.just(Playlist.builder().song("MiniKore").build());
    }

}
