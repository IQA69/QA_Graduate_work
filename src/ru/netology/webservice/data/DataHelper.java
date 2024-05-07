package ru.netology.webservice.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {

    }

    public static Card getApprovedCard() {
        return new Card("4444444444444441", generateCurrentMonth(),
                generateCurrentYear(), generateName("en"), generateValidCvc());
    }

    public static Card getDeclinedCard() {
        return new Card("4444444444444442", generateCurrentMonth(),
                generateCurrentYear(), generateName("en"), generateValidCvc());
    }

    public static Card getEmptyCard() {
        return new Card("", generateCurrentMonth(), generateCurrentYear(),
                generateName("en"), generateValidCvc());
    }

    public static Card getAllNullSymbolsNumberCard() {
        return new Card("1111111111111111", generateCurrentMonth(), generateCurrentYear(),
                generateName("en"), generateValidCvc());
    }
    public static Card getCvcEmptyFieldCard() {
        return new Card("4444444444444441", generateCurrentMonth(), generateCurrentYear(),
                generateName("en"), "");
    }


    public static Card getRandomInvalidCard() {
        return new Card(generateInvalidCardNumber(), generateCurrentMonth(),
                generateCurrentYear(), generateName("en"), generateValidCvc());
    }

    public static Card getRandomInvalidPastMonth() {
        return new Card("4444444444444441", generatePastMonth(),
                generateCurrentYear(), generateName("en"), generateValidCvc());
    }

    public static Card getRandomInvalidMonthNullSymbol() {
        return new Card("4444444444444441", generateInvalidNullMonth(),
                generateCurrentYear(), generateName("en"), generateValidCvc());
    }

    public static Card getRandomInvalidMonth() {
        return new Card("4444444444444441", generateInvalidMonth(),
                generateCurrentYear(), generateName("en"), generateValidCvc());
    }
    public static Card getRandomInvalidPastYear() {
        return new Card("4444444444444441", generateCurrentMonth(),
                generatePastYear(), generateName("en"), generateValidCvc());
    }
    public static Card getRandomInvalidFutureYear() {
        return new Card("4444444444444441", generateCurrentMonth(),
                generateFutureYear(), generateName("en"), generateValidCvc());
    }


    public static Card getRandomFullName() {
        return new Card("4444444444444441", generateCurrentMonth(),
                generateCurrentYear(), generateFullName("en"), generateValidCvc());
    }

    public static Card getRandomRussianName() {
        return new Card("4444444444444441", generateCurrentMonth(),
                generateCurrentYear(), generateName("ru"), generateValidCvc());
    }

    public static Card getRandomOnlyFirstName() {
        return new Card("4444444444444441", generateCurrentMonth(),
                generateCurrentYear(), generateOnlyFirstName("en"), generateValidCvc());
    }
    public static Card getRandomOnlyLastName() {
        return new Card("4444444444444441", generateCurrentMonth(),
                generateCurrentYear(), generateOnlyLastName("en"), generateValidCvc());
    }
    public static Card getRandomInvalidCvc() {
        return new Card("4444444444444441", generateRandomMonth(),
                generateFutureYear(), generateName("en"), generateInvalidCvc());
    }

    public static Card getRandomInvalidCvcTwoSymbols() {
        return new Card("4444444444444441", generateRandomMonth(),
                generateFutureYear(), generateName("en"), generateFutureYear());
    }

    public static Card getOwnerEmptyFieldCard() {
        return new Card("4444444444444441", generateCurrentMonth(),
                generateCurrentYear(), "", generateValidCvc());
    }
    public static Card getMonthEmptyFieldCard() {
        return new Card("4444444444444441", "",
                generateCurrentYear(), generateName("en"), generateValidCvc());
    }

    public static Card getYearEmptyFieldCard() {
        return new Card("4444444444444441", generateCurrentMonth(),
                "", generateName("en"), generateValidCvc());
    }
    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generateFullName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().fullName();
    }

    public static String generateOnlyFirstName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().firstName();
    }

    public static String generateOnlyLastName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().lastName();
    }
    public static String generateValidCardNumber() {
        Faker faker = new Faker();
        return faker.numerify("****************");
    }

    public static String generateInvalidCardNumber() {
        Faker faker = new Faker();
        return faker.numerify("***************");
    }

    public static String generateRandomMontаh() {
        int month = (int)((Math.random() * 16) + 1);;
        return String.format("@5c0", month);

    }

    public static String generateInvalidMonth() {
        int month = (int) (17 + Math.random() * (99 - 17 + 1));
        return String.format("@5c0", month);
    }


    public static String generatePastMonth() {
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue() - 1;
        return String.format("@5c0", month);
    }

    public static String generateInvalidNullMonth() {
        return ("00");
    }



    public static String generateCurrentYear() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear() - 2000;
        return Integer.toString(year);
    }

    public static String generateCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateFutureYear() {
        Random random = new Random();
        int i = random.nextInt(7) + 7;
        LocalDate futureDate = LocalDate.now().plusYears(i);
        int year = futureDate.getYear() - 2000;
        return Integer.toString(year);
    }

    public static String generatePastYear() {
        int pastYear = (int)((Math.random() * 24) - 1);;
        return String.format("@5c0", pastYear);
    }


    public static String generateValidCvc() {
        Faker faker = new Faker();
        return faker.numerify("***");
    }

    public static String generateInvalidCvc() {
        Faker faker = new Faker();
        return faker.numerify("**");
    }

    @Value
    public static class Card {
        String number;
        String year;
        String month;
        String holder;
        String cvc;
    }
}