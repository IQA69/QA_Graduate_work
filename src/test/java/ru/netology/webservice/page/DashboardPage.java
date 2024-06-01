package ru.netology.webservice.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private static final SelenideElement heading = $$("h2").find(Condition.text("Путешествие дня"));
    private static final SelenideElement payButton = $$("button").find(exactText("Купить"));
    private static final SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public PaymentGatePage goToPayPage() {
        payButton.click();
        return new PaymentGatePage();
    }

    public CreditGatePage goToCreditPage() {
        creditButton.click();
        return new CreditGatePage();
    }
}