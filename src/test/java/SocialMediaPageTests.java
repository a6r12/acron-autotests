import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class SocialMediaPageTests extends TestBase {

    static Stream<Arguments> mixedArgumentsTestDataProvider() {
        return Stream.of(
//                Arguments.of(0, "https://www.acron.ru/contacts", "Акрон"), - не в рамках параметризованного
                Arguments.of(1, "https://vk.com/acronpjsc", ".label", "email"),
                Arguments.of(2, "https://www.youtube.com/channel/UCKxgdzy7CMqws5Z6TR_UuqA", ".style-scope ytd-masthead", "Войти"),
                Arguments.of(3, "https://t.me/acron_official", ".tgme_head", "DOWNLOAD"),
                Arguments.of(4, "https://zen.yandex.ru/id/622af6a0ee026a77e11f92e8", ".channel-title", "Акрон" )
        );
    }

    @MethodSource(value = "mixedArgumentsTestDataProvider")
    @ParameterizedTest(name = "Have a content in offical Acron social media in Russia: {1}")
    void mixedArgumentsTest(int arg, String url, String selectorName, String expectedResult) {
        open(baseUrl);
        // Перейти в раздел информации о компании
        $("[class*='burger js-burger']").click();
        // Открыть официальные источники
        $("ul.menu__socials li", arg).click();
        // Проверить что открылась страница с оффициальным источником
        switchTo().window(1);
        webdriver().shouldHave(url(url));
        $(selectorName).shouldHave(text(expectedResult)); //проверка наличия контента на странице
    }
}
