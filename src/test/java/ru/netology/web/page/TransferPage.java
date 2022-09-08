package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement transferAmount = $("[data-test-id=amount] input");
    private SelenideElement minTransferAmount = $("[data-test-id=amount] .input__control");
    private SelenideElement CardTransfer = $("[data-test-id=from] input");
    private SelenideElement clickButton = $("[data-test-id=action-transfer]");
    private SelenideElement notification = $("[data-test-id='error-notification'] .notification__content");

    public void transferData(int value, String cardNumber) {
        transferAmount.setValue(Integer.toString(value));
        CardTransfer.setValue(cardNumber);
        clickButton.click();
    }

    public void getMinTransferAmount() {
        minTransferAmount.shouldHave(exactText("Ошибка")).shouldBe(visible);
    }

    public void getNotification() {
        notification.shouldHave(exactText("Ошибка")).shouldBe(visible);
    }
}
