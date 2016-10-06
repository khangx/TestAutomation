package com.qa.pages;

import com.qa.webdriver.QAFactory;
import org.openqa.selenium.WebDriver;

/**
 * Created by Robbie on 10/5/2016.
 */
public class Movie extends QAFactory {
    public Movie(WebDriver driver){
        super(driver);
    }

    public MovieVerify verify(){ return new MovieVerify(this);}
}
