package com.gomel.taf.notebook_shop.pages;

import com.gomel.taf.framework.utils.Constants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.gomel.taf.framework.ui.SeleniumUI.getInstance;

public class LoginPage {
    private final By loginInput = By.id("loginform-username");
    private final By passwordInput = By.id("loginform-password");
    private final By loginButton = By.name("login-button");

    @Step(value = "Open Login page")
    public LoginPage openPage() {
        getInstance().get(Constants.URL_LOG_IN);
        return this;
    }

    @Step(value = "Input login")
    public LoginPage inputLogin() {
        getInstance().type(loginInput, Constants.USER_LOGIN);
        return this;
    }

    @Step(value = "Input password")
    public LoginPage inputPassword() {
        getInstance().type(passwordInput, Constants.USER_PASSWORD);
        return this;
    }

    @Step(value = "Click Submit button")
    public LoginPage clickConfirm() {
        getInstance().click(loginButton);
        return this;
    }
}
