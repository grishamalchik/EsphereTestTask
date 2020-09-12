package agevalidator;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;


public class AgeValidator {
    /**
     *  Метод возвращает true, если человеку 18 или более лет.
     *
     * @param   birthDate   Дата рождения с часовым поясом.
     */
    public static boolean isAdult(ZonedDateTime birthDate) {
        //если передали пустой объект - кидаем исключение
        if (birthDate == null) throw new IllegalArgumentException();

        //дату рождения нужно привести к локальной дате
        LocalDate birthDateLocal = birthDate.toLocalDate();
        LocalDate currentDate = LocalDate.now();

        //дата рождения не должна быть в будущем
        if (birthDateLocal.isAfter(currentDate))
            throw new IllegalArgumentException();

        return Period.between(birthDateLocal, LocalDate.now()).getYears() >= 18;
    }
}
