package com.gomel.taf.framework.ui;

import com.gomel.taf.framework.factory.BrowserFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumUI implements WrapsDriver {
    private static final int WAIT_TIMEOUT_SECONDS = 5;
    private static final ThreadLocal<SeleniumUI> instance = new ThreadLocal<>();

    private WebDriver wrappedWebDriver;

    public SeleniumUI() {
        wrappedWebDriver = BrowserFactory.getBrowser();
    }

    public static synchronized SeleniumUI getInstance() {
        if (instance.get() == null) {
            instance.set(new SeleniumUI());
        }
        return instance.get();
    }

    public WebDriver getWrappedDriver() {
        return wrappedWebDriver;
    }

    public void stopBrowser() {
        try {
            if (getWrappedDriver() != null) {
                getWrappedDriver().quit();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            instance.remove();
        }
    }

    public void get(String url) {
        wrappedWebDriver.get(url);
    }

    public String getCurrentUrl() {
        return wrappedWebDriver.getCurrentUrl();
    }

    public void click(By by) {
        WebElement element = waitForVisibilityOfElement(by);
        element.click();
    }

    public void type(By by, String keys) {
        WebElement element = waitForVisibilityOfElement(by);
        element.sendKeys(keys);
    }

    public void clear(By by) {
        wrappedWebDriver.findElement(by).sendKeys(Keys.chord(Keys.LEFT_CONTROL + "a"));
    }

    public void clearAndType(By by, String value) {
        clear(by);
        type(by, value);
    }

    public String getText(By by) {
        return waitForVisibilityOfElement(by).getText();
    }

    public boolean isVisible(By by) {
        try {
            waitForVisibilityOfElement(by);
        } catch (WebDriverException e) {
            return false;
        }
        return true;
    }

    public WebElement waitForVisibilityOfElement(By by) {
        return new WebDriverWait(wrappedWebDriver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS)).
                until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public List<WebElement> waitForVisibilityOfElements(By by) {
        return new WebDriverWait(wrappedWebDriver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public void waitForNonVisibilityOfElement(By by) {
        new WebDriverWait(wrappedWebDriver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
