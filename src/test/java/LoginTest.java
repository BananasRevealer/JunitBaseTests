import PageObjects.LoginPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.selenide.LogType;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.$x;

public class LoginTest {

    private final Supplier<ConditionFactory> Waiter = () -> Awaitility.given()
            .ignoreExceptions()
            .pollInterval(3, TimeUnit.SECONDS)
            .await()
            .dontCatchUncaughtExceptions()
            .atMost(10, TimeUnit.SECONDS);

//    private boolean waitLogs(String expectedMessage) {
//        WebDriver driver = WebDriverRunner.getWebDriver();
//        AtomicBoolean isLogContains = new AtomicBoolean(false);
//
//        Waiter.get().until(() -> {
//            LogEntries logEntries = driver.manage().logs();
//        });
//
//    }

    @BeforeAll
    public static void driverSetup() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences loggingPreferences = new LoggingPreferences();

        loggingPreferences.enable(String.valueOf(LogType.BROWSER), Level.ALL);
        loggingPreferences.enable(String.valueOf(LogType.PERFORMANCE), Level.ALL);

        capabilities.setCapability("goog:loggingPrefs",loggingPreferences);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        Configuration.browserCapabilities = capabilities;
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 10000;


    }


    @Test
    public void userLoginTest () {
        Selenide.open("http://dzen.ru");
        LoginPage loginPage = new LoginPage();
        loginPage.login("user", "password");
        $x("//h6[contains(text(),Dashboard]..").shouldBe(Condition.visible);
        $x("//img[@alt = 'profile picture']..").shouldBe(Condition.visible, Condition.enabled);
    }
}
