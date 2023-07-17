package PageObjects;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private final SelenideElement
    usernameInput = $x("//input[contains(@name, \"name\")]"),
    passwordInput = $x("//input[contains(@name, \"password\")]"),
    submitButton = $x("//input[contains(@type, \"submit\")]");

    public LoginPage login(String name, String password) {
        usernameInput.sendKeys(name);
        passwordInput.sendKeys(password);
        submitButton.click();

        return this;
    }

}
