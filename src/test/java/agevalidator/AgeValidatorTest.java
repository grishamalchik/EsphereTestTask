package agevalidator;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class AgeValidatorTest {
    @Test
    public void becameEighteenYesterdayIsAdult(){
        //arrange
        LocalDate birthDate = LocalDate.now().minusYears(18).minusDays(1);
        //act
        boolean isAdult = AgeValidator.isAdult(birthDate);
        //assert
        Assert.assertTrue(isAdult);
    }

    @Test
    public void becameEighteenTodayIsAdult(){
        //arrange
        LocalDate birthDate = LocalDate.now().minusYears(18);
        //act
        boolean isAdult = AgeValidator.isAdult(birthDate);
        //assert
        Assert.assertTrue(isAdult);
    }

    @Test
    public void willBecomeEighteenTomorrowIsNotAdult(){
        //arrange
        LocalDate birthDate = LocalDate.now().minusYears(18).plusDays(1);
        //act
        boolean isAdult = AgeValidator.isAdult(birthDate);
        //assert
        Assert.assertFalse(isAdult);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void passingNullWillThrowException(){
        //arrange
        LocalDate birthDate = LocalDate.now().plusDays(1);
        //act
        boolean isAdult = AgeValidator.isAdult(birthDate);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void passingFutureDateWillThrowException(){
        //arrange
        LocalDate birthDate = null;
        //act
        boolean isAdult = AgeValidator.isAdult(birthDate);
    }
}