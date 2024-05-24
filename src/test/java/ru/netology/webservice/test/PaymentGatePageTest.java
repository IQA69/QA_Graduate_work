package ru.netology.webservice.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.webservice.data.DataHelper;
import ru.netology.webservice.data.SQLHelper;
import ru.netology.webservice.page.DashboardPage;
import ru.netology.webservice.page.PaymentGatePage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentGatePageTest {
    DashboardPage dashboardPage = open("http://localhost:8080/", DashboardPage.class);

    @AfterEach
    public void cleanBase() throws SQLException {
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
    void verifyCardAllowedForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getApprovedCard();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkSuccessNotification();
        assertEquals("APPROVED", SQLHelper.getCardRequestStatus());
    }


    @Test
    void verifyCardBakedForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getDeclinedCard();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkErrorNotification();
        assertEquals("DECLINED", SQLHelper.getCardRequestStatus());
    }

    @Test
    void verifyAllEmptySymbolsNumberForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getAllNullSymbolsNumberCard();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getCardRequestStatus());
    }

    @Test
    void verifyInvalidCardNumberForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getRandomInvalidCard();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void verifyValidCardNumberForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getRandomValidCard();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void verifyInvalidEmptyMonthForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getRandomInvalidMonthNullSymbol();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongExpiryDateNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void verifyInvalidMonthForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getRandomInvalidMonth();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongExpiryDateNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void verifyInvalidPreviousMonthForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getRandomInvalidPastMonth();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongExpiryDateNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void verifyInvalidPreviousYearForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getRandomInvalidPastYear();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkCardExpiredNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void verifyInvalidFutureYearForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getRandomInvalidFutureYear();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongExpiryDateNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void verifyRandomOnlyFirstNameForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getRandomOnlyFirstName();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void verifyRandomOnlyLastNameForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getRandomOnlyLastName();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void verifyRandomFullNameForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getRandomFullName();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void verifyRussianNameForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getRandomRussianName();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void verifyRandomInvalidCvcForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getRandomInvalidCvc();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void verifyInvalidCvcTwoSymbolsForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getRandomInvalidCvcTwoSymbols();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void verifyCardNumberEmptyFieldForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getEmptyCard();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void verifyMonthEmptyFieldForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getMonthEmptyFieldCard();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void verifyYearEmptyFieldForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getYearEmptyFieldCard();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void verifyOwnerEmptyFieldForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getOwnerEmptyFieldCard();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkFieldRequiredNotification();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void verifyCvcEmptyFieldForCard() {
        dashboardPage.cardForm();
        var card = DataHelper.getCvcEmptyFieldCard();
        var buyPage = new PaymentGatePage();
        buyPage.fillInCardData(card);
        buyPage.checkWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
}