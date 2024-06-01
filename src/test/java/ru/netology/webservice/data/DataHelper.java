package ru.netology.webservice.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    public static Card getApprovedCard() {
        return new Card("4444444444444441", "10", "24", "Svetlana Sokolova", "789");
    }

    public static Card getDeclinedCard() {
        return new Card("4444444444444442", "10", "24", "Svetlana Sokolova", "789");
    }

    public static Card getEmptyCard() {
        return new Card("", "","","","");
    }

    public static String getDisplacedMonth() {
        int shift = (int) (Math.random() * 12);
        return LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getDisplacedYear(int yearCount) {
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static Card getNumberCard14Symbols() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getDisplacedMonth();
        String year = getDisplacedYear(2);
        String cvc = faker.number().digits(3);
        String number = faker.number().digits(14);
        return new Card(number, month, year, holder, cvc);
    }

    public static Card getCardNotInDatabase() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getDisplacedMonth();
        String year = getDisplacedYear(2);
        String cvc = faker.number().digits(3);
        return new Card("5444444444444444", month, year, holder, cvc);
    }

    public static Card getCardMonth1Symbol() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = faker.number().digit();
        String year = getDisplacedYear(1);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardMonthAbove12() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getDisplacedYear(1);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", "13", year, holder, cvc);
    }

    public static Card getCardMonth00CurrentYear() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getDisplacedYear(0);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", "00", year, holder, cvc);
    }

    public static Card getCardMonth00AboveCurrentYear() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getDisplacedYear(1);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", "00", year, holder, cvc);
    }

    public static Card getCardYear1Symbol() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getDisplacedMonth();
        String year = faker.number().digit();
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardYearAboveCurrentYear4() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getDisplacedMonth();
        String year = getDisplacedYear(4);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardYearPreviousCurrentYear() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getDisplacedMonth();
        String year = getDisplacedYear(1);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardYear00() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getDisplacedMonth();
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, "00", holder, cvc);
    }

    public static Card getCardCvc2Symbols() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getDisplacedMonth();
        String year = getDisplacedYear(1);
        String cvc = faker.number().digits(2);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardCvc1Symbol() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getDisplacedMonth();
        String year = getDisplacedYear(1);
        String cvc = faker.number().digits(1);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardOwner1Name() {
        Faker faker = new Faker();
        String holder = faker.name().firstName();
        String month = getDisplacedMonth();
        String year = getDisplacedYear(1);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardOwnerRussian() {
        Faker faker = new Faker(new Locale("ru"));
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getDisplacedMonth();
        String year = getDisplacedYear(1);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardOwnerNumbers() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.number().digit();
        String month = getDisplacedMonth();
        String year = getDisplacedYear(1);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardSpecialSymbols() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " %$ * &";
        String month = getDisplacedMonth();
        String year = getDisplacedYear(1);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvc);
    }
}