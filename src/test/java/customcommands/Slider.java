package customcommands;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.$;
public class Slider {

    SelenideElement selector;
    public Slider(SelenideElement selector){
        this.selector=selector;
    }

    public String getTemperature(){
        return selector.shouldBe(Condition.visible).getText();
    }

    public Slider selectTemperature(String celcius){
        selector.click();
        return this;
    }

}
