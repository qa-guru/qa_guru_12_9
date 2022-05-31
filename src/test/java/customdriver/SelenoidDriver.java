package customdriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class SelenoidDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities desiredCapabilities) {
        var caps = new DesiredCapabilities();
        var options = Map.of("enableVnc", true, "enableVideo", true);
        caps.setCapability("selenoid:options", options);
        //caps.setBrowserName(System.getProperty("selenide.browserName"));
        //caps.setVersion(System.getProperty("selenide.browserVersion"));
        caps.setBrowserName("chrome");
        Configuration.browserCapabilities = caps;
        caps = caps.merge(desiredCapabilities);
        RemoteWebDriver driver = null;
        var selenoidUrl = System.getProperty("selenide.grid");
        try {
            driver = new RemoteWebDriver(new URL(selenoidUrl), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
