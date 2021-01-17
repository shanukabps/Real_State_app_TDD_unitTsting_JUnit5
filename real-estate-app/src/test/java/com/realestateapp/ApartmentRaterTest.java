package com.realestateapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class ApartmentRaterTest {

    @ParameterizedTest(name = "{index} => expected={0}, area={1}, price={2}")
    @DisplayName("ParametrizeTest using csv arguments")
    @CsvSource({
            "-1, 0.0, 2.78786767",
            "-1,0.0,1234.44432"
    })
    void testRateApartment(int expected, double area, BigDecimal price) {
        ApartmentRater apr = new ApartmentRater();
        Apartment ap = new Apartment(area, price);
        Assertions.assertEquals(expected, apr.rateApartment(ap), "not correct area ");
    }

    @ParameterizedTest
    @DisplayName("PrameterizedTest inject data using method")
    @MethodSource("provideApratment")
    public void testRaterApartment(int expected, double area, BigDecimal price) {
        ApartmentRater apr = new ApartmentRater();
        Apartment ap = new Apartment(area, price);
        Assertions.assertEquals(expected, apr.rateApartment(ap), "method expected " + expected + " area " + area + " price " + price);

    }

    private static Stream<Arguments> provideApratment() {
        return Stream.of(
                Arguments.of(-1, 0.0, new BigDecimal(200.331)),
                Arguments.of(-1, 0.0, new BigDecimal(1000.336)),
                Arguments.of(0, 23.3, new BigDecimal(4000)),
                Arguments.of(1, 1, new BigDecimal(7000)),
                Arguments.of(2, 1, new BigDecimal(200000))

        );
    }

    @ParameterizedTest
    @MethodSource("ApartmentList")
    @DisplayName("Testing _CalculateAverageRatingQ")
    public void testCalculateAverageRating(List<Apartment> apratment) {

        ApartmentRater apartmentRater = new ApartmentRater();
        Assertions.assertEquals(-0.25, apartmentRater.calculateAverageRating(apratment));

    }

    private static Stream<Apartment> ApartmentList() {
        Apartment ap1= new Apartment(0.0, new BigDecimal(200.331));
        Apartment ap2=  new Apartment(0.0, new BigDecimal(1000.336));
        Apartment ap3=  new Apartment(23.3, new BigDecimal(4000));
        Apartment ap4=  new Apartment(1, new BigDecimal(7000));
        List<Apartment> ap =Arrays.asList(ap1,ap2,ap3,ap4);

        List list = List.of(ap);
        return list.stream();
    }

    @ParameterizedTest
    @MethodSource("EmptyApartmentList")
    @DisplayName("Testing CalculateAverageRating Exceptions")
    public void testingCalculateAverageRatingExceptions(List<Apartment> apratment){
        ApartmentRater apartmentRater = new ApartmentRater();
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            apartmentRater.calculateAverageRating(apratment);
        });
        String expectedMessage = "Cannot calculate average rating for empty list";
        String actualMessage = exception.getMessage();


        Assertions.assertEquals(actualMessage,expectedMessage);
    }

    private static Stream<Apartment> EmptyApartmentList() {

        List<Apartment> ap =Arrays.asList();

        List list = List.of(ap);
        return list.stream();
    }

}







