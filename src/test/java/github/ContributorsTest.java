package github;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ContributorsTest {

  @Test
  void solntsevShouldBeFirstContributor(){

    Configuration.browserSize="1200x400";
    // open repository page
    open("https://github.com/selenide/selenide");
    // bring mouse over the first avatar on contributors tab
    $(".Layout-sidebar").$(byText("Contributors")).ancestor("div")
            .$("ul li").hover();

    // check: popup is showing Andres Solntsev
    $$(".Popover-message").findBy(visible).shouldHave(text("Andrei Solntsev"));

  }


}
