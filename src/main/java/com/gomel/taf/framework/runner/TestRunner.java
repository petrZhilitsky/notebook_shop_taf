package com.gomel.taf.framework.runner;

import org.testng.TestNG;

import java.util.Collections;

public class TestRunner {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        testNG.setTestSuites(Collections.singletonList("src/main/resources/testng.xml"));
        testNG.run();
    }
}
