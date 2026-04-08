package com.gmail.utils;

import io.qameta.allure.Step;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

import static com.gmail.utils.Constants.PROPERTY_FILE;

public class PropertyLoader {
    private static Properties myProperties;
    public static InputStream inputStream;

    @Step
    public static Properties propertyLoader(){
        if(Objects.isNull(myProperties)) {
            System.out.println("myProperties is null, Loading once");
            myProperties = new Properties();
            try {
                myProperties.load(resourceLoader(PROPERTY_FILE));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return myProperties;
    }

    @Step
    public static String getMyProperty(String property){
        if(Objects.isNull(myProperties))
            propertyLoader();
        return myProperties.getProperty(property);
    }

    @Step
    public static InputStream resourceLoader(String file){
        inputStream = PropertyLoader.class.getClassLoader().getResourceAsStream(file);
        if(Objects.isNull(inputStream)){
            try {
                System.out.println(file+ "is not in class path");
                inputStream = Files.newInputStream(Path.of(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return inputStream;
    }
}