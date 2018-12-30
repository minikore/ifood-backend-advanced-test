package com.minikore.weather.playlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.util.TimeZone;

@EnableWebFlux
@SpringBootApplication(scanBasePackages = "com.minikore")
public class WeatherPlaylistApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherPlaylistApplication.class, args);
    }

    private static void setupEnvironment() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, currentEnv());
    }

    private static String currentEnv() {
        String scope = System.getenv("SCOPE");

        if (scope == null) {
            return "development";
        }

        return scope;
    }
}