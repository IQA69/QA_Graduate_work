package ru.netology.webservice.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.webservice.data.DataHelper;
import ru.netology.webservice.data.SQLHelper;
import ru.netology.webservice.page.CreditGatePage;
import ru.netology.webservice.page.DashboardPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditGatePageTest {
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
        void verifyCardAllowedForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getApprovedCard();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkSuccessNotification();
            assertEquals("APPROVED", SQLHelper.getCreditRequestStatus());
        }


        @Test
        void verifyCardBakedForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getDeclinedCard();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkErrorNotification();
            assertEquals("DECLINED", SQLHelper.getCardRequestStatus());
        }

        @Test
        void verifyAllEmptySymbolsNumberForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getAllNullSymbolsNumberCard();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkWrongFormat();
            assertEquals("0", SQLHelper.getCardRequestStatus());
        }

        @Test
        void verifyInvalidCardNumberForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getRandomInvalidCard();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkWrongFormat();
            assertEquals("0", SQLHelper.getOrderCount());
        }

        @Test
        void verifyInvalidEmptyMonthForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getRandomInvalidMonthNullSymbol();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkWrongExpiryDateNotification();
            assertEquals("0", SQLHelper.getOrderCount());
        }

        @Test
        void verifyInvalidMonthForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getRandomInvalidMonth();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkWrongExpiryDateNotification();
            assertEquals("0", SQLHelper.getOrderCount());
        }
        @Test
        void verifyInvalidPreviousMonthForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getRandomInvalidPastMonth();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkWrongExpiryDateNotification();
            assertEquals("0", SQLHelper.getOrderCount());
        }


        @Test
        void verifyInvalidPreviousYearForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getRandomInvalidPastYear();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkCardExpiredNotification();
            assertEquals("0", SQLHelper.getOrderCount());
        }

        @Test
        void verifyInvalidFutureYearForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getRandomInvalidFutureYear();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkWrongExpiryDateNotification();
            assertEquals("0", SQLHelper.getOrderCount());
        }

        @Test
        void verifyRandomOnlyFirstNameForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getRandomOnlyFirstName();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkWrongFormat();
            assertEquals("0", SQLHelper.getOrderCount());
        }

        @Test
        void verifyRandomOnlyLastNameForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getRandomOnlyLastName();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkWrongFormat();
            assertEquals("0", SQLHelper.getOrderCount());
        }

        @Test
        void verifyRussianNameForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getRandomRussianName();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkWrongFormat();
            assertEquals("0", SQLHelper.getOrderCount());
        }
        @Test
        void verifyInvalidCvcTwoSymbolsForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getRandomInvalidCvc();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkWrongFormat();
            assertEquals("0", SQLHelper.getOrderCount());
        }

        @Test
        void verifyCardNumberEmptyFieldForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getEmptyCard();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkWrongFormat();
            assertEquals("0", SQLHelper.getOrderCount());
        }

        @Test
        void verifyMonthEmptyFieldForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getMonthEmptyFieldCard();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkWrongFormat();
            assertEquals("0", SQLHelper.getOrderCount());
        }

        @Test
        void verifyYearEmptyFieldForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getYearEmptyFieldCard();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkWrongFormat();
            assertEquals("0", SQLHelper.getOrderCount());
        }
        @Test
        void verifyOwnerEmptyFieldForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getOwnerEmptyFieldCard();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkFieldRequiredNotification();
            assertEquals("0", SQLHelper.getOrderCount());
        }
        @Test
        void verifyCvcEmptyFieldForCredit() {
            dashboardPage.creditForm();
            var card = DataHelper.getCvcEmptyFieldCard();
            var creditPage = new CreditGatePage();
            creditPage.fillInCardData(card);
            creditPage.checkWrongFormat();
            assertEquals("0", SQLHelper.getOrderCount());
        }
    }