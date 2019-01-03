package com.minikore.weather.playlist.controller.api.request;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Set;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.junit.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class GeoCoordinatesParamRequestTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldNotHaveAnyViolationsWhenLatAndLogAreOk() {

        GeoCoordinatesParamRequest paramRequest = GeoCoordinatesParamRequest.builder()
            .lat(-23.1857)
            .lon(-46.8978)
            .build();

        Set<ConstraintViolation<GeoCoordinatesParamRequest>> violations = validator.validate(paramRequest);

        assertThat(violations, is(empty()));
    }

    @Test
    public void throwsNotNullViolationWhenLatIsNull() {

        GeoCoordinatesParamRequest paramRequest = GeoCoordinatesParamRequest.builder()
            .lon(-46.8978)
            .build();
        validateViolationOnField(paramRequest, "lat", NotNull.class);
    }

    @Test
    public void throwsMinViolationWhenLatIsLessThenMinus90() {

        Object paramRequest = GeoCoordinatesParamRequest.builder()
            .lat(-90.0001)
            .lon(-180.000)
            .build();

        validateViolationOnField(paramRequest, "lat", DecimalMin.class);
    }

    @Test
    public void throwsMaxViolationWhenLatIsGraterThen90() {

        Object paramRequest = GeoCoordinatesParamRequest.builder()
            .lat(90.0001)
            .lon(-180.000)
            .build();

        validateViolationOnField(paramRequest, "lat", DecimalMax.class);
    }

    @Test
    public void throwsNotNullViolationWhenLonIsNull() {

        Object paramRequest = GeoCoordinatesParamRequest.builder()
            .lat(-23.1857)
            .build();

        validateViolationOnField(paramRequest, "lon", NotNull.class);
    }

    @Test
    public void throwsMinViolationWhenLonIsLessThenMinus180() {

        Object paramRequest = GeoCoordinatesParamRequest.builder()
            .lat(-23.1857)
            .lon(-180.001)
            .build();

        validateViolationOnField(paramRequest, "lon", DecimalMin.class);
    }

    @Test
    public void throwsMaxViolationWhenLonIsGraterThen180() {

        Object paramRequest = GeoCoordinatesParamRequest.builder()
            .lat(90.0000)
            .lon(-180.001)
            .build();

        validateViolationOnField(paramRequest, "lon", DecimalMin.class);
    }

    private void validateViolationOnField(Object object, String field, Class<?> type) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object);

        assertThat(violations, hasSize(1));

        ConstraintViolation<?> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString(), is(field));
        assertThat(violation.getConstraintDescriptor().getAnnotation(), is(instanceOf(type)));
    }
}