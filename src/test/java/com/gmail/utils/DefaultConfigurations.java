package com.gmail.utils;

import io.qameta.allure.Step;

import java.security.PrivateKey;
import java.util.Properties;

public class DefaultConfigurations {

    @Step("Loading the Global-Default configurations")
    public static void loadDefaultProperties(){
        System.out.println("In loadDefaultProperties");
        Properties myProperties = PropertyLoader.propertyLoader();
        for(String property : PropertyLoader.propertyLoader().stringPropertyNames()){
            System.out.println("Current value of property "+property+" is "+ myProperties.getProperty(property));
            //System.out.println(System.getProperties().stringPropertyNames().toString());
            if (System.getProperties().containsKey(property)){
                System.out.println("The property "+property+" is overriden from CLI, Updating its value");
                System.out.println(property+" Old value : "+ myProperties.getProperty(property));
                myProperties.setProperty(property, System.getProperty(property));
                System.out.println(property+" New value : "+ myProperties.getProperty(property));
            }
        }
    }
}