package com.minikore.weather.playlist.controller.api.request;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CityNameParamRequest {

    @Valid
    @NotBlank
    @Size(max = 100)
    private String city;
}
