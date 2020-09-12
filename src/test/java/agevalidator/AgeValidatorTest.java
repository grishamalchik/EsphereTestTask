package agevalidator;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.ZonedDateTime;

public class AgeValidatorTest {
    @Test
    public void becameEighteenYesterdayIsAdult(){
        //arrange
        ZonedDateTime birthDate = ZonedDateTime.now().minusYears(18).minusDays(1);
        //act
        boolean isAdult = AgeValidator.isAdult(birthDate);
        //assert
        Assert.assertTrue(isAdult);
    }

    @Test
    public void becameEighteenTodayIsAdult(){
        //arrange
        ZonedDateTime birthDate = ZonedDateTime.now().minusYears(18);
        //act
        boolean isAdult = AgeValidator.isAdult(birthDate);
        //assert
        Assert.assertTrue(isAdult);
    }

    @Test
    public void willBecomeEighteenTomorrowIsNotAdult(){
        //arrange
        ZonedDateTime birthDate = ZonedDateTime.now().minusYears(18).plusDays(1);
        //act
        boolean isAdult = AgeValidator.isAdult(birthDate);
        //assert
        Assert.assertFalse(isAdult);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void passingNullWillThrowException(){
        //arrange
        ZonedDateTime birthDate = ZonedDateTime.now().plusDays(1);
        //act
        boolean isAdult = AgeValidator.isAdult(birthDate);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void passingFutureDateWillThrowException(){
        //arrange
        ZonedDateTime birthDate = null;
        //act
        boolean isAdult = AgeValidator.isAdult(birthDate);
    }
}