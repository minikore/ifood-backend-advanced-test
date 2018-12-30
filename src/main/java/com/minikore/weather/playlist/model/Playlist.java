package com.minikore.weather.playlist.model;

import lombok.*;

import java.util.List;

@Data
@Builder
public class Playlist {

    @Singular
    private List<String> songs;
}
