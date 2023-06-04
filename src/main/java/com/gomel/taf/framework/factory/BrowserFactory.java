package com.gomel.taf.framework.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BrowserFactory {
    private static final int WAIT_TIMEOUT_SECONDS = 10;

    private BrowserFactory() {
    }

    public static WebDriver getBrowser() {
        WebDriver webDriver;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--ignore-certificate-errors");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(options);

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        webDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));

        return webDriver;
    }
}
