package com.gomel.taf.framework.utils;

import com.gomel.taf.framework.ui.SeleniumUI;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Start test " + iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test " + iTestResult.getName() + " succeded");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.err.println("Test " + iTestResult.getName() + " failed");
        saveScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.err.println("Test " + iTestResult.getName() + " skipped");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println(iTestContext.getName() + " started");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println(iTestContext.getName() + " stopped");
    }

    @Attachment(value = "Test failure screenshot", type = "image/png")
    private byte[] saveScreenshot() {
        return ((TakesScreenshot) SeleniumUI.getInstance().getWrappedDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
