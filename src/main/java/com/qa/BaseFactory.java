package com.qa;

import com.qa.webdriver.QAFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Created by Robbie on 9/23/2016.
 */
public abstract class BaseFactory extends QAFactory implements BaseFactoryImp {
    @BeforeTest
    public void testSetup(){
        init("http://localhost", 4444);
        get(config.getString("test.url"));
    }

    @AfterTest
    public void testTearDown(){
        driver.quit();
    }

    @AfterSuite
    public void suiteTearDown(){
        try {
            driver.quit();
        }catch (Exception e){}
    }
}
