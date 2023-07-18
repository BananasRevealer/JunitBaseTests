package TestUtils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.jupiter.api.BeforeAll;



import java.util.logging.Level;

@Slf4j
public class TestSetup {


    @BeforeAll
    public static void setListener() {
        SelenideLogger.addListener("Allure",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @BeforeEach
    @SneakyThrows
    public void setupDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences loggingPreferences = new LoggingPreferences();

        loggingPreferences.enable(String.valueOf(LogType.BROWSER), Level.ALL);
        loggingPreferences.enable(String.valueOf(LogType.PERFORMANCE), Level.ALL);

        options.addArguments("--remote-allow-origins=*");

        capabilities.setCapability("goog:loggingPrefs",loggingPreferences);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        Configuration.browser = options.getBrowserName();
        Configuration.browserCapabilities = capabilities;
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 10000;
        Configuration.baseUrl = "https://www.yoox.com/ge/myoox/login";
        String chromeDriverKey = "webdriver.chrome.driver";
        if (System.getProperty(chromeDriverKey) == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\ITxa\\drivers\\chromedriver.exe");
        }
    }
}







