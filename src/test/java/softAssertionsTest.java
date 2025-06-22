import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class softAssertionsTest {

    @BeforeAll
    static void basicSettingBrowder() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void softAssertionGithubTest() {
        // открыть страницу github
        open("https://github.com");
        // нажать на поиск, ввести в поле воода selenide и нажать enter
        $(".header-search-button").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        // нажать на selenide/selenide == перый разультат поиска
        // нажать на первый результат поиска
        $("span.search-match").click();
        // перейдите в раздел Wiki проекта
        $(byText("Wiki")).click();
        // убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-wrapper").shouldHave(text("Soft assertions"));
        // откройте страницу SoftAssertions
        $("a[href='/selenide/selenide/wiki/SoftAssertions']").click();
        // скроллим до нужного нам заголовка с 3. Using JUnit5 extend test class:
        $("[id='user-content-3-using-junit5-extend-test-class']")
                .scrollIntoView(true)
                .shouldBe(visible);
        // проверьте что внутри есть ПРИМЕР кода для JUnit5
        String expectedSnippet = """
            @ExtendWith({SoftAssertsExtension.class})
            class Tests {
                @Test
                void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                }
            }""";
        $("#gollum-markdown-content").shouldHave(text(expectedSnippet));
    }
}
