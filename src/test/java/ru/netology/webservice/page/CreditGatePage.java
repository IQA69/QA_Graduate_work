package ru.netology.webservice.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import ru.netology.webservice.data.Card;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditGatePage {

    private final SelenideElement heading = $$("h3").find(exactText("Кредит по данным карты"));
    private final SelenideElement cardNumber =  $(byText("Номер карты")).parent().$("[class=\"input__control\"]");
    private final SelenideElement cardMonth = $(byText("Месяц")).parent().$("[class=\"input__control\"]");
    private final SelenideElement cardYear = $(byText("Год")).parent().$("[class=\"input__control\"]");
    private final SelenideElement cardOwner = $(byText("Владелец")).parent().$("[class=\"input__control\"]");
    private final SelenideElement cvcCode = $(byText("CVC/CVV")).parent().$("[class=\"input__control\"]");

    private final SelenideElement successNotification = $(byText("Операция одобрена Банком.")).parent().$("[class=\"notification__content\"]");
    private final SelenideElement errorNotification = $(byText("Ошибка! Банк отказал в проведении операции.")).parent().$("[class=\"notification__content\"]");
    private final SelenideElement wrongFormatNotification = $(byText("Неверный формат"));
    @Getter
    private final ElementsCollection wrongFormatNotificationElement = $$(byText("Неверный формат"));

    private final SelenideElement wrongExpiryDateNotification = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpiredNotification = $(byText("Истёк срок действия карты"));
    private final SelenideElement fieldRequiredNotification = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement cancelField = $$("[class=\"icon-button__text\"]").first();
    private final SelenideElement continueButton = $(byText("Продолжить"));


    public CreditGatePage() {
        heading.shouldBe(visible);
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

    public void inputData(Card card) {
        cardNumber.setValue(card.getCardNumber());
        cardMonth.setValue(card.getMonth());
        cardYear.setValue(card.getYear());
        cardOwner.setValue(card.getCardHolder());
        cvcCode.setValue(card.getCvc());
        continueButton.click();
    }
}