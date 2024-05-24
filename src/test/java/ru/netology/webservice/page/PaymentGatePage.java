package ru.netology.webservice.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.webservice.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PaymentGatePage {

    private final SelenideElement cardNumber = $("[placeholder='1111 1111 1111 1111']");
    private final SelenideElement cardMonth = $("[placeholder='03']");
    private final SelenideElement cardYear = $("[placeholder='15']");
    private final SelenideElement cardOwner = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvcCode = $("[placeholder='333']");

    private final SelenideElement continueButton = $(byText("Продолжить"));
    private final SelenideElement successNotification = $(".notification_status_ok");
    private final SelenideElement errorNotification = $(".notification_status_error");
    private final SelenideElement wrongFormatNotification = $(byText("Неверный формат"));


    private final SelenideElement wrongExpiryDateNotification = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpiredNotification = $(byText("Истёк срок действия карты"));
    private final SelenideElement fieldRequiredNotification = $(byText("Поле обязательно для заполнения"));

    public PaymentGatePage() {
        SelenideElement paymentGatePageHeading = $(byText("Оплата по карте"));
        paymentGatePageHeading.shouldBe(visible);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void checkSuccessNotification() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
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
        fieldRequiredNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void fillInCardData(DataHelper.Card card) {
        cardNumber.setValue(card.getNumber());
        cardMonth.setValue(card.getMonth());
        cardYear.setValue(card.getYear());
        cardOwner.setValue(card.getHolder());
        cvcCode.setValue(card.getCvc());
        clickContinueButton();
    }

}