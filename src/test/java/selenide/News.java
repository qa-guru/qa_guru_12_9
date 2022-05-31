package selenide;

import com.codeborne.selenide.*;
import customcommands.Slider;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.openqa.selenium.*;

import java.io.*;
import java.time.Duration;

import static com.codeborne.selenide.ClipboardConditions.content;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class News {

    @Test
    void capabilites(){
        // -Dselenide.browser=customdriver.SelenoidDriver
        Configuration.browser="customerdriver.SelenoidDriver";
        open("");
    }

    @Test
    void emailConfirmation(){
        // https://github.com/vinogradoff/test-data-broker
        var links=2; // request to databroker
        Assumptions.assumeTrue(links>1,"There was no links to test");
        open("link");

    }

    @Test
    void processEngine(){
        $("").shouldHave(text("sdfklj"));
        $("").shouldHave(text("sdfklj"));
        $("").shouldHave(text("sdfklj"));
        $("tickets").shouldHave(text("100 tickets found"),Duration.ofSeconds(60));
        $("").shouldHave(text("sdfklj"));
        $("").shouldHave(text("sdfklj"));
    }
    @Test
    void languageJetBrains(){
        $("[data-test=language-grid]").$(byText("Русский")).click();
    }
    @Test
    void inputHints(){

        Configuration.browserSize="1900x1200";
        open("https://amekana.com/");
        $(".recommendation-modal__button").shouldBe(visible, Duration.ofSeconds(25)).click();
        $("a span.icon-search").click();
        $$("[name=q]").findBy(visible).setValue("a");
        sleep(10000);
    }

    @Test
    void webdriver(){
        System.setProperty("wdm.chromeDriverVersion","100.0.1252");
        var driver=WebDriverRunner.getWebDriver();
    }

    @Test
    void sliderTest(){
        var temp=new Slider($(".slider")).selectTemperature("75")
                .getTemperature();
    }

    @Test
    void softAssert(){
        // arrange
        // act
        // assert
        // assert
        // assert
        // assert
        // assert
        // assertAll
        Configuration.assertionMode=AssertionMode.SOFT;
        $("").shouldBe(visible);
        $("").shouldBe(visible);
        $("").shouldBe(visible);

        // TODO assertAll
    }
    @Test
    @DisabledIfSystemProperty(named = "selenide.remote", matches = "http.*", disabledReason = "Clipboard not supported on Selenium Grid yet")
    @EnabledIfSystemProperty(named = "environment", matches = "staging", disabledReason = "....")
    void clipboards() {
        open("https://moskva.mts.ru/personal");
        Selenide.clipboard().setText("1234324234");
        $("[name=number]").sendKeys(Keys.COMMAND + "v");
        $("[name=number]").shouldHave(value("(123) 432-42-34"));

        // open page with repository
        // find element with copy url - function
        // click copy-button
        //Assertions.assertEquals(Selenide.clipboard().getText(), "https://github.com/selenide/selenide.git");
        clipboard().shouldHave(content("https://github.com/selenide/selenide.git"));
    }

    @Test
    void ownTextTest() {
        open("https://github.com");
        $("#home-code h3").shouldHave(exactText("Record or rewind any change to your code to keep you and your team in sync."));

    }

    @Test
    void ownText2() {
        open("https://www.tutorialrepublic.com/snippets/preview.php?topic=bootstrap&file=elegant-contact-form");
        $(".alert-info").shouldHave(exactOwnText("on to learn how to customize this layout further. Bootstrap 3 version of this snippet is ."));
    }


    void snapshotsTest() {

        ElementsCollection checkboxes = $$("checkbox").filter(visible);   // Page has 100+  elements
        for (int i = 0; i < 10; i++) {
            checkboxes.get(i).shouldBe(enabled);
        }

        ElementsCollection checkboxesFast = $$("checkbox").filter(visible).snapshot();
        for (int i = 0; i < 10; i++) {
            checkboxesFast.get(i).shouldBe(enabled);
        }

        ElementsCollection collection = $$("").snapshot();
        collection.get(4).shouldHave(text("abc"));
        $("button").click();
        collection.get(5).shouldHave(text("abc"));
    }


}
