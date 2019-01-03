package com.minikore.weather.playlist.client.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
public class OpenWeatherResponse {

    Double temp;

    @SuppressWarnings("unchecked")
    @JsonProperty("main")
    private void unpackNestedMain(Map<String,Object> main) {
        this.temp = (Double) main.get("temp");
    }

}
