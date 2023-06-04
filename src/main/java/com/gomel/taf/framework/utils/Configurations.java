package com.gomel.taf.framework.utils;

import java.util.Properties;

public final class Configurations {
    private Properties properties;
    private static Configurations instance = null;

    private Configurations() {
        this.properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static Configurations getInstance() {
        if (instance == null) {
            instance = new Configurations();
        }
        return instance;
    }

    public String getProperty(String key) {
        String value = null;
        if (key != null && !key.trim().isEmpty()) {
            value = this.properties.getProperty(key);
        }
        return value;
    }
}
