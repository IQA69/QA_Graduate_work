package ru.netology.webservice.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.webservice.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditGatePage {
    private SelenideElement creditGatePageHeading = $(byText("Кредит по данным карты"));

    private SelenideElement cardNumber = $("[placeholder='1111 1111 1111 1111']");
    private SelenideElement cardMonth = $("[placeholder='03']");
    private SelenideElement cardYear = $("[placeholder='15']");
    private SelenideElement cardOwner = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvcCode = $("[placeholder='333']");

    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement successNotification = $(".notification_status_ok");
    private SelenideElement errorNotification = $(".notification_status_error");
    private SelenideElement wrongFormatNotification = $(byText("Неверный формат"));
    private ElementsCollection wrongFormatNotificationElement = $$(byText("Неверный формат"));

    private SelenideElement wrongExpiryDateNotification = $(byText("Неверно указан срок действия карты"));
    private SelenideElement cardExpiredNotification = $(byText("Истёк срок действия карты"));
    private SelenideElement fieldRequiredNotification = $(byText("Поле обязательно для заполнения"));

    public CreditGatePageTest() {
        creditGatePageHeading.shouldBe(visible);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void checkSuccessNotification() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void fillInCardData(DataHelper.Card card) {
        cardNumber.setValue(card.getCardNumber());
        cardMonth.setValue(card.getMonth());
        cardYear.setValue(card.getYear());
        cardOwner.setValue(card.getCardHolder());
        cvcCode.setValue(card.getCvc());
        clickContinueButton();
    }

    public void checkErrorNotification() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void checkWrongFormat() {
        wrongFormatNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void checkWrongExpiryDateNotification() {
        wrongExpiryDateNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void checkCardExpiredNotification() {
        cardExpiredNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void checkFieldRequiredNotification() {
        fieldRequiredNotification.shouldBe(visible, Duration.ofSeconds(15));
    }
}