package com.minikore.weather.playlist.controller.api.request;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.junit.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class CityNameParamRequestTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldNotHaveAnyViolationsWhenTheCityIsCorrect() {

        CityNameParamRequest cityNameParamRequest = CityNameParamRequest.builder()
            .city("Taumatawhakatangihangakoauauotamateapokaiwhenuakitanatahu")
            .build();
        Set<ConstraintViolation<CityNameParamRequest>> violations = validator.validate(cityNameParamRequest);

        assertThat(violations, is(empty()));
    }

    @Test
    public void throwsNotBlankViolationWhenIsEmptyCity() {

        CityNameParamRequest cityNameParamRequest = CityNameParamRequest.builder()
            .city("")
            .build();
        Set<ConstraintViolation<CityNameParamRequest>> violations = validator.validate(cityNameParamRequest);

        assertThat(violations, hasSize(1));

        ConstraintViolation<?> violation = violations.iterator().next();
        assertThat(violation.getConstraintDescriptor().getAnnotation(), is(instanceOf(NotBlank.class)));
    }

    @Test
    public void throwsNotBlankViolationWhenIsNullCity() {

        CityNameParamRequest cityNameParamRequest = CityNameParamRequest.builder()
            .build();
        Set<ConstraintViolation<CityNameParamRequest>> violations = validator.validate(cityNameParamRequest);

        assertThat(violations, hasSize(1));

        ConstraintViolation<CityNameParamRequest> violation = violations.iterator().next();
        assertThat(violation.getConstraintDescriptor().getAnnotation(), is(instanceOf(NotBlank.class)));
    }
    @Test
    public void throwsMaxSizeViolationWhenCityIsMoreThen100() {

        CityNameParamRequest cityNameParamRequest = CityNameParamRequest.builder()
            .city("THE_LARGEST_CITY_NAME_IN_THE_WORLD_HAS_85_CHARACTERS_MAYBE_CUT_AT_100_CHARACTERS_MAKE_ANY_SENSE_LOL__")
            .build();
        Set<ConstraintViolation<CityNameParamRequest>> violations = validator.validate(cityNameParamRequest);

        assertThat(violations, hasSize(1));

        ConstraintViolation<CityNameParamRequest> violation = violations.iterator().next();
        assertThat(violation.getConstraintDescriptor().getAnnotation(), is(instanceOf(Size.class)));
    }
}