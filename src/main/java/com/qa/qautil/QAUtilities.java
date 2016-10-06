package com.qa.qautil;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Robbie on 10/6/2016.
 */
public class QAUtilities {
    /**
     * Parse the WebElement and get the Selenium By locators for output logging
     * @param webElement - WebElement
     * @return String of Selenium By
     */
    public static String getByFromWebElement(WebElement webElement){
        List<String> locators = new ArrayList<>();
        String elementReference = webElement.toString();
        Pattern pattern = Pattern.compile("(-> )(.*?)]",Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(elementReference);
        while(matcher.find()){
            locators.add(matcher.group(2));
        }
        return locators.toString();
    }
}
