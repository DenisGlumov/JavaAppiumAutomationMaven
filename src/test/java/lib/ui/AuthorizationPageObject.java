package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{
    private static final String
                //LOGIN_BUTTON = "xpath://body/div/a[text()='Log in']",
                LOGIN_BUTTON = "css:body > div.drawer-container.view-border-box > div.drawer.drawer-container__drawer.position-fixed.visible > a",

                LOGIN_INPUT = "css:input[name='wpName']",
                PASSWORD_INPUT = "css:input[name='wpPassword']",
                SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting and click button authorization")
    public void clickAuthButton() {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);
    }

    @Step("Enter login and password for authorization")
    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login,"Cannot find and put a login to the login input", 5 );
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password,"Cannot find and put a password to the password input", 5 );
    }

    @Step("Waiting and click by submitForm")
    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click auth button", 5);
    }

    @Step("Click by button login")
    public void clickButtonLoginIn() {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    LOGIN_BUTTON,
                    "Cannot find navigation button to My list",
                    5
            );
        }
    }
}
