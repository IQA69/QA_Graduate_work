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

public class PaymentGatePageTest {
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
    void verifyApprovedCardForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getApprovedCard());
        payment.checkSuccessNotification();
        assertEquals("APPROVED", "APPROVED");
    }

    @Test
    void verifyDeclinedCardForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.checkSuccessNotification();
        assertEquals("DECLINED", "DECLINED");
    }

    @Test
    void verifyInvalidCardNumber14SymbolsForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getNumberCard14Symbols());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardNotInDatabaseForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getCardNotInDatabase());
        payment.checkErrorNotification();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardMonth1SymbolForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getCardMonth1Symbol());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardMonthAbove12ForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getCardMonthAbove12());
        payment.checkWrongExpiryDateNotification();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardMonth00CurrentYearForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getCardMonth00CurrentYear());
        payment.checkWrongExpiryDateNotification();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardMonth00AboveCurrentYearForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getCardMonth00AboveCurrentYear());
        payment.checkCardExpiredNotification();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardYear1SymbolForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getCardYear1Symbol());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardYearAboveCurrentYear4ForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getCardYearAboveCurrentYear4());
        payment.checkWrongExpiryDateNotification();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardYearPreviousCurrentYearForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getCardYearPreviousCurrentYear());
        payment.checkErrorNotification();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardYear00ForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getCardYear00());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardCvc2SymbolsForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getCardCvc2Symbols());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardCvc1SymbolForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getCardCvc1Symbol());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardOwner1NameForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getCardOwner1Name());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardEmptyAllFieldForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getEmptyCard());
        payment.checkFieldRequiredNotification();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardOwnerRussianForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getCardOwnerRussian());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }

    @Test
    void verifyInvalidCardOwnerNumbersForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getCardOwnerNumbers());
        payment.checkWrongFormat();
        assertEquals("0","0");
    }

    @Test
    void verifyInvalidCardSpecialSymbolsForCard() {
        val startPage = new DashboardPage();
        val payment = startPage.goToPayPage();
        payment.inputData(DataHelper.getCardSpecialSymbols());
        payment.checkWrongFormat();
        assertEquals("0", "0");
    }
}