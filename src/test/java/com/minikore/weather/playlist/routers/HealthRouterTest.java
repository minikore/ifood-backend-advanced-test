package com.minikore.weather.playlist.routers;

import com.minikore.weather.playlist.WeatherPlaylistApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WeatherPlaylistApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthRouterTest {
    private static final String PING_URI = "/ping";

    private WebTestClient webTestClient;

    @Autowired
    private HealthRouter router;

    @Before
    public void setUp() {
        webTestClient = WebTestClient
            .bindToRouterFunction(router.createHealthRoute())
            .build();
    }

    @Test
    public void shouldRespondPongToPingRequest() {
        webTestClient.get()
            .uri(PING_URI)
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .consumeWith(model -> assertThat(model.getResponseBody(), is("pong")));
    }
}