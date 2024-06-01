package ru.netology.webservice.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.webservice.data.DataHelper;
import ru.netology.webservice.data.SQLHelper;
import ru.netology.webservice.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditGatePageTest {
    public static String url = System.getProperty("sut.url");

    @BeforeEach
    public void openPage() {
        open(url);
    }

    @AfterEach
    public void cleanBase() {
        SQLHelper.cleanDatabase();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void verifyApprovedCardForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getApprovedCard());
        payment.checkSuccessNotification();
        assertEquals("APPROVED", "APPROVED");
    }

    @Test
    void verifyDeclinedCardForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.checkSuccessNotification();
        assertEquals("DECLINED", "DECLINED");
    }

    @Test
    void verifyInvalidCardNumber14SymbolsForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getNumberCard14Symbols());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardNotInDatabaseForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardNotInDatabase());
        payment.checkErrorNotification();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardMonth1SymbolForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardMonth1Symbol());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardMonthAbove12ForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardMonthAbove12());
        payment.checkWrongExpiryDateNotification();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardMonth00CurrentYearForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardMonth00CurrentYear());
        payment.checkWrongExpiryDateNotification();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardMonth00AboveCurrentYearForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardMonth00AboveCurrentYear());
        payment.checkCardExpiredNotification();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardYear1SymbolForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardYear1Symbol());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardYearAboveCurrentYear4ForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardYearAboveCurrentYear4());
        payment.checkWrongExpiryDateNotification();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardYearPreviousCurrentYearForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardYearPreviousCurrentYear());
        payment.checkErrorNotification();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardYear00ForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardYear00());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardCvc2SymbolsForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardCvc2Symbols());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardCvc1SymbolForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardCvc1Symbol());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardOwner1NameForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardOwner1Name());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardEmptyAllFieldForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getEmptyCard());
        payment.checkFieldRequiredNotification();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardOwnerRussianForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardOwnerRussian());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardOwnerNumbersForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardOwnerNumbers());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardSpecialSymbolsForCredit() {
        val startPage = new DashboardPage();
        val payment = startPage.goToCreditPage();
        payment.inputData(DataHelper.getCardSpecialSymbols());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }
}