package github;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class FirstContributerTest {

    @Test
    void firstContrubuterShouldBeAndreiSolntsev(){
        // открыть страничку репозитория Селенида
        open("https://github.com/selenide/selenide");
        // подвести мышку к первому элементу в области Contributors
        //$(".Layout-sidebar").$(byText("Contributors")).closest("div")
        //                .$$("ul li").first().hover();

        $$(".Layout-sidebar .BorderGrid-row").find(text("Contributors"))
                .$$("ul li").first().hover();
        // check: в появившемся окошке (оверлей) текст Andrei Solntsev
        $$(".Popover-message").find(visible).shouldHave(text("Andrei Solntsev"));

    }
}
