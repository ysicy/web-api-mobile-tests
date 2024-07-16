package tests.api;


import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SelenoidTestBase {

    private RemoteWebDriver createSelenoidDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Map<String,Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", true);
        capabilities.setCapability("selenoid:options", selenoidOptions);
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL(System.getenv("host")),capabilities);
        remoteWebDriver.setFileDetector(new LocalFileDetector());
        return  remoteWebDriver;
    }


    private WebDriver createLocalDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        SelenideConfig config = new SelenideConfig()
                .browser(options.getBrowserName())
                .browserCapabilities(options);
        SelenideDriver driver = new SelenideDriver(config);
        return driver.getAndCheckWebDriver();
    }
}
