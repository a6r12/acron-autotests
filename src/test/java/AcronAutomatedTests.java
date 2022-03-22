import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static io.qameta.allure.Allure.step;

public class AcronAutomatedTests extends TestBase {

    @Test
    @DisplayName("Check burger menu: open search page")
    void checkSearchPage() {
        step("Открыть главную страницу", () -> {
            open(baseUrl);
        });
        step("Кликнуть на бургер", () -> {
            $("[class*='burger js-burger']").click();
        });
        step("Открыть страницу с поиском по сайту", () -> {
            $("a[href*=\"/search/\"]").click();
        });
        step("Страница с поиском по сайту открылась", () -> {
            $(".page-title").shouldHave(text("Поиск по сайту"));
        });
    }

    @Test
    @DisplayName("Search validation: search section with vacancies")
    void searchValidation() {
        step("Открыть страницу поиска по сайту", () -> {
            open(baseUrl + "search/");
        });
        step("Ввести в поле поиска значение \"Вакансии\"", () -> {
            $("#search-input").setValue("Вакансии").pressEnter();
        });
        step("Найти секцию вакансии", () -> {
            $(".search-item", 1).shouldHave(text("Вакансии")).click();
        });
        step("Выбрать вакансию тестировщика", () -> {
            $(byText("QA Engineer")).scrollTo().click();
        });
        step("Появилось кнопка откликнуться", () -> {
            $("button[class*=\"btn btn--big js-vacancy-response js-open-popup\"]", 3)
                    .$("span")
                    .shouldBe(visible)
                    .shouldHave(text("Откликнуться"));
        });
    }

    @Test
    @DisplayName("Displaying information from the exchange")
    void searchExchangeInfo() {
        step("Открыть страницу поиска по сайту", () -> {
            open(baseUrl);
        });
        step("Проверка наличия блока информации с биржи", () -> {
            $(".stock__link ul li").shouldHave(text("MOEX:AKRN"));
        });
    }

    @Test
    @DisplayName("Displaying information about fertilizer")
    void checkBuyFertilizer() {
        step("Открыть страницу поиска по сайту", () -> {
            open(baseUrl);
        });
        step("Кликнуть на бургер", () -> {
            $("[class*='burger js-burger']").click();
        });
        step("Открыть страницу \"Купить удобрения\"", () -> {
            $(".menu__inner .btn").click();
        });
        step("Открылась страница \"Купить удобрения\"", () -> {
            switchTo().window(1);
            webdriver().shouldHave(url("https://market.acron.ru/"));
            $("#section_banner").shouldHave(text("Агромаркет АКРОН"));
        });
    }
}

