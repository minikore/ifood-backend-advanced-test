package com.minikore.weather.playlist.client.weather.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class OpenWeatherRequestInterceptor implements RequestInterceptor {

    public static final String APP_ID = "APPID";
    private static final String UNITS = "units";

    @Value("${openweather.api.appid}")
    String appId;

    @Value("${openweather.api.units}")
    String units;

    @Override
    public void apply(RequestTemplate template) {
        template.query(APP_ID, appId);
        template.query(UNITS, units);
    }
}
