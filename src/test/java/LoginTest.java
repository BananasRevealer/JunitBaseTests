import PageObjects.LoginPage;
import TestUtils.TestSetup;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$x;

public class LoginTest extends TestSetup {
    private static Stream<Arguments> users () {
        return Stream.of(
                Arguments.of("89253164020","Testpass!@Testp@ss1"),
                Arguments.of("+79253164020","Testpass!@Testp@ss2"),
                Arguments.of("79653408374","Testpass!@Testp@ss3"),
                Arguments.of("89112151010","Testpass!@Testp@ss4")
        );
    }

    @ParameterizedTest
    @MethodSource("users")
    void userLoginTest (String user, String password) {
        Allure.step("", () -> {
            Selenide.open(baseUrl);
            LoginPage loginPage = new LoginPage();
            loginPage.login(user, password);
            $x("//h6[contains(text(),Dashboard]/..").shouldBe(Condition.visible);
            $x("//img[@alt = 'profile picture']/..").shouldBe(Condition.visible, Condition.enabled);
        });
    }
}
