package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class AccountPage {
    // locators
    public SelenideElement
            accountPageName = $("#section_banner");

    public AccountPage checkPage() {
        accountPageName.shouldHave(text("Личный кабинет"));

        return this;
    }
}
