package com.qa.pages;

import com.qa.webdriver.QAFactory;
import org.openqa.selenium.WebDriver;

/**
 * Created by Robbie on 9/23/2016.
 */
public class InTheaters extends QAFactory{

    public InTheaters(WebDriver driver){
        super(driver);
    }

    public InTheatersVerify verify(){ return new InTheatersVerify(this);}
}
