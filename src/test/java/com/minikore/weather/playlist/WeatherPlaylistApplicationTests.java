package com.minikore.weather.playlist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.TimeZone;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeatherPlaylistApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherPlaylistApplicationTests {

	@Test
	public void contextLoads() {
	    assertThat(TimeZone.getDefault(), is(TimeZone.getTimeZone("UTC")));
	}
}

