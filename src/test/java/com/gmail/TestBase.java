package com.gmail;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

import static com.gmail.utils.DefaultConfigurations.loadDefaultProperties;

public class TestBase {

    @Step
    @BeforeSuite
    public void suiteSetup() {
        System.out.println(" In Before suite ");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        loadDefaultProperties();
    }

}