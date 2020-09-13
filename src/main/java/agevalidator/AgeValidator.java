package agevalidator;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator {
    /**
     *  Метод валидации возраста человека.
     *
     * @param   birthDate   Дата рождения.
     * @return {@code true}, если человеку 18 или более лет, иначе {@code false}
     */
    public static boolean isAdult(LocalDate birthDate) {
        //если передали пустой объект - кидаем исключение
        if (birthDate == null) throw new IllegalArgumentException();

        LocalDate currentDate = LocalDate.now();

        //дата рождения не должна быть в будущем
        if (birthDate.isAfter(currentDate))
            throw new IllegalArgumentException();

        return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
    }
}