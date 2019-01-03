package com.minikore.weather.playlist.controller.api.response;

import lombok.*;

import java.util.List;

@Data
@Builder
public class PlaylistResponse {

    @Singular
    private List<String> songs;
}
