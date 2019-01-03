package com.minikore.weather.playlist.controller.api.request;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class GeoCoordinatesParamRequest {

    @Valid
    @NotNull
    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private Double lat;

    @Valid
    @NotNull
    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private Double lon;
}