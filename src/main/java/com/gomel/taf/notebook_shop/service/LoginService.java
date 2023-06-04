package com.gomel.taf.notebook_shop.service;

import com.gomel.taf.notebook_shop.pages.LoginPage;

public class LoginService {
    public void logIn() {
        new LoginPage()
                .openPage()
                .inputLogin()
                .inputPassword()
                .clickConfirm();
    }
}
