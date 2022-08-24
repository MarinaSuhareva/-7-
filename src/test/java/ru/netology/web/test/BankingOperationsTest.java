
package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import lombok.val;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.BalancePage;
import ru.netology.web.page.LoginPageV2;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;

class BankingOperationsTest {
    @BeforeEach
    void shouldLogin() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void transferMoneyToSecondCard() {
        int value = 100000;
        String cardNumber = String.valueOf(DataHelper.getFirstCardNumber());
        val balancePage = new BalancePage();
        var firstCardBalance = balancePage.getFirstCardBalance();
        var secondCardBalance = balancePage.getSecondCardBalance();
        balancePage.secondButton();
        val transferPage = new TransferPage();
        transferPage.transferData(value, cardNumber);
        var firstCardBalance1 = balancePage.getFirstCardBalance();
        var secondCardBalance1 = balancePage.getSecondCardBalance();
        Assertions.assertEquals(firstCardBalance - value, firstCardBalance1);
        Assertions.assertEquals(secondCardBalance + value, secondCardBalance1);

    }


    @Test
    void TransferMoneyToFirstCard() {
        int value = 10000;
        String cardNumber = String.valueOf(DataHelper.getSecondCardNumber());
        val balancePage = new BalancePage();
        var firstCardBalance = balancePage.getFirstCardBalance();
        var secondCardBalance = balancePage.getSecondCardBalance();
        balancePage.firstButton();
        val transferPage = new TransferPage();
        transferPage.transferData(value, cardNumber);
        var firstCardBalance1 = balancePage.getFirstCardBalance();
        var secondCardBalance1 = balancePage.getSecondCardBalance();
        Assertions.assertEquals(secondCardBalance - value, secondCardBalance1);
        Assertions.assertEquals(firstCardBalance + value, firstCardBalance1);

    }

    @Test
    void transferMoneyToSecondCard2() {
        int value = 0;
        String cardNumber = String.valueOf(DataHelper.getFirstCardNumber());
        val balancePage = new BalancePage();
        var firstCardBalance = balancePage.getFirstCardBalance();
        var secondCardBalance = balancePage.getSecondCardBalance();
        balancePage.secondButton();
        val transferPage = new TransferPage();
        transferPage.transferData(value, cardNumber);
        var firstCardBalance1 = balancePage.getFirstCardBalance();
        var secondCardBalance1 = balancePage.getSecondCardBalance();
        Assertions.assertEquals(firstCardBalance - value, firstCardBalance1);
        Assertions.assertEquals(secondCardBalance + value, secondCardBalance1);
        transferPage.getMinTransferAmount();
    }


    @Test
    void TransferMoneyToFirstCard2() {
        int value = 100000;
        String cardNumber = String.valueOf(DataHelper.getSecondCardNumber());
        val balancePage = new BalancePage();
        var firstCardBalance = balancePage.getFirstCardBalance();
        var secondCardBalance = balancePage.getSecondCardBalance();
        balancePage.firstButton();
        val transferPage = new TransferPage();
        transferPage.transferData(value, cardNumber);
        var firstCardBalance1 = balancePage.getFirstCardBalance();
        var secondCardBalance1 = balancePage.getSecondCardBalance();
        Assertions.assertEquals(secondCardBalance - value, secondCardBalance1);
        Assertions.assertEquals(firstCardBalance + value, firstCardBalance1);
        transferPage.getNotification();
    }
}
