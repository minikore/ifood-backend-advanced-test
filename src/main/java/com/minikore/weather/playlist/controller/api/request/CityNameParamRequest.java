package com.minikore.weather.playlist.controller.api.request;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
public class CityNameParamRequest {

    @Valid
    @NotBlank
    @Size(max = 100)
    private String city;
}
