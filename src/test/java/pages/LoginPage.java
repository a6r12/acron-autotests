package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    // locators
    public SelenideElement
            authorizationPageName = $("#section_banner"),
            emailInput = $(".form__input", 0),
            passwordInput = $(".form__input", 1),
            submitInput = $(".btn"),
            accountPageName = $("#section_banner");


    // actions
    public LoginPage openPage(String urlMarket) {
        open(urlMarket + "/personal");
        authorizationPageName.shouldHave(text("Авторизация"));

        return this;
    }

    public LoginPage setEmail(String email) {
        emailInput.setValue(email);

        return this;
    }

    public LoginPage setPassword(String password) {
        passwordInput.setValue(password);

        return this;
    }

    public LoginPage submitForm() {
        submitInput.click();

        return this;
    }

}
