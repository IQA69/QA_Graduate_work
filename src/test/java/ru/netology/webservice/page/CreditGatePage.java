package ru.netology.webservice.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import ru.netology.webservice.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditGatePage {

    private final SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement cardMonth = $("[placeholder='08']");
    private final SelenideElement cardYear = $("[placeholder='22']");
    private final SelenideElement cardOwner = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvcCode = $("[placeholder='999']");

    private final SelenideElement continueButton = $(byText("Продолжить"));
    private final SelenideElement successNotification = $(".notification_status_ok");
    private final SelenideElement errorNotification = $(".notification_status_error");
    private final SelenideElement wrongFormatNotification = $(byText("Неверный формат"));
    @Getter
    private final ElementsCollection wrongFormatNotificationElement = $$(byText("Неверный формат"));

    private final SelenideElement wrongExpiryDateNotification = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpiredNotification = $(byText("Истёк срок действия карты"));
    private final SelenideElement fieldRequiredNotification = $(byText("Поле обязательно для заполнения"));

    public SelenideElement creditGatePageHeading = $(byText("Кредит по данным карты"));

    public CreditGatePage() {
        creditGatePageHeading.shouldBe(visible);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void checkSuccessNotification() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
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

    public void fillInCardData(DataHelper.Card card) {
        cardNumber.setValue(card.getNumber());
        cardMonth.setValue(card.getMonth());
        cardYear.setValue(card.getYear());
        cardOwner.setValue(card.getHolder());
        cvcCode.setValue(card.getCvc());
        clickContinueButton();
    }

}