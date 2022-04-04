package selenide;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.openqa.selenium.Keys;

import java.io.*;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class News {

    static boolean serverGotCorrectRequest = false;


    @Test
    void screenshoting() throws IOException {
        var file1 = $("").screenshot();
        var arr = new FileInputStream(file1).readAllBytes();
        File file3 = $("").download();
        var arr2 = new byte[5];
        Assertions.assertEquals(arr, arr2);
    }

    @Test
    void checkPDF() throws IOException {
        File pdfFile = $("").download();
        var pdf = new PDF(pdfFile);
        assertThat(pdf).containsText("Invoice");
    }

    @Test
    void testStyle() {
        $("").shouldHave(cssValue("top", "70px"));

        $("").click(ClickOptions.usingDefaultMethod().offset(-10, -10));
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
        Assertions.assertEquals(Selenide.clipboard().getText(), "https://github.com/selenide/selenide.git");


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
