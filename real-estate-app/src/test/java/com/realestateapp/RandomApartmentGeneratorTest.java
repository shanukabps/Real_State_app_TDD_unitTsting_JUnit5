package com.realestateapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class RandomApartmentGeneratorTest {

    @RepeatedTest(100)
    @DisplayName("generate Method test for 100times DefaultMinAreaMinPrice")
    public void randomGenerator(){
        RandomApartmentGenerator rag=new RandomApartmentGenerator();
        Apartment generateApratment1=rag.generate();
        Apartment generateApratment2=rag.generate();
        Assertions.assertNotEquals(generateApratment1,generateApratment2);
        System.out.println("Apartment Area =  "+generateApratment1.getArea()+" Apartment price = "+generateApratment1.getPrice());
    }

    @RepeatedTest(100)
    @DisplayName("generate Method test for 100times with  Custom MinAreaMinPrice")
    public void randomGeneratorCustom(){
        RandomApartmentGenerator rag=new RandomApartmentGenerator(40,new BigDecimal(4000));
        Apartment generateApratment1=rag.generate();
        Apartment generateApratment2=rag.generate();
        Assertions.assertNotEquals(generateApratment1,generateApratment2);
        System.out.println("Apartment Area =  "+generateApratment1.getArea()+" Apartment price = "+generateApratment1.getPrice());
    }
}
