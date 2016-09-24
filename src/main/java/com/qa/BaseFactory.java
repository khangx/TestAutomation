package com.qa;

import com.qa.webdriver.QAFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by Robbie on 9/23/2016.
 */
public abstract class BaseFactory extends QAFactory implements BaseFactoryImp {

    @BeforeMethod
    public void testSetup(){
        init("http://localhost",4444);
        get(config.getString("test.url"));
    }

    @AfterMethod
    public void testTearDown(){
        driver.quit();
    }
}
