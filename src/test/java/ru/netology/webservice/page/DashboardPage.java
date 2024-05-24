package ru.netology.webservice.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private static final SelenideElement buttonPayCard = $(byText("Купить"));
    private static final SelenideElement buttonPayCredit = $(byText("Купить в кредит"));

    public DashboardPage() {
        SelenideElement dashboardPageHeading = $(byText("Путешествие дня"));
        dashboardPageHeading.shouldBe(visible);
    }

    public void cardForm() {
        buttonPayCard.click();
        new PaymentGatePage();
    }

    public void creditForm() {
        buttonPayCredit.click();
        new CreditGatePage();
    }


}