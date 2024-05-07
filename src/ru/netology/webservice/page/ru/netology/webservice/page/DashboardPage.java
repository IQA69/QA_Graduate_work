package ru.netology.webservice.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement dashboardPageHeading = $(byText("Путешествие дня"));
    private static SelenideElement elementPayCard = $(byText("Купить"));
    private static SelenideElement elementPayCredit = $(byText("Купить в кредит"));

    public DashboardPage() {
        dashboardPageHeading
                .shouldBe(visible);
    }

    public PaymentGatePage selectPayCard() {
        elementPayCard.click();
        return new PaymentGatePage();
    }

    public CreditGatePage selectPayCredit() {
        elementPayCredit.click();
        return new CreditGatePage();
    }
}