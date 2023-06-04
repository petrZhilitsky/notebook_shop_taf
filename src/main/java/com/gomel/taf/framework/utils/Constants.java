package com.gomel.taf.framework.utils;

public class Constants {
    private Constants() {
    }

    public static final String USER_LOGIN = Configurations.getInstance().getProperty("user.login");
    public static final String USER_PASSWORD = Configurations.getInstance().getProperty("user.password");
    public static final String URL_LOG_IN = Configurations.getInstance().getProperty("login.url");
    public static final String URL_MAIN = Configurations.getInstance().getProperty("main.url");
}
