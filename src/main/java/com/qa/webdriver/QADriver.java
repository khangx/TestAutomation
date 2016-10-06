package com.qa.webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Robbie on 9/23/2016.
 */
public class QADriver implements WebDriver {
    protected WebDriver driver;
    protected Wait<WebDriver> waitr;

    synchronized public void init(String baseUrl, Integer port){
        try {
            if(port != null) {
                driver = new RemoteWebDriver(new URL(baseUrl+":"+port+"/wd/hub"), getDesiredCapabilities());
            }else{
                driver = new RemoteWebDriver(new URL(baseUrl+":4444/wd/hub"), getDesiredCapabilities());
            }
        } catch (Exception e) {
            driver = new FirefoxDriver(getDesiredCapabilities());
        }


    }

    protected void waiter(ExpectedCondition expectedCondition, final Integer timeout){
        new FluentWait(driver)
                .ignoring(NoSuchElementException.class)
                .pollingEvery(800, TimeUnit.MILLISECONDS)
                .withTimeout(timeout, TimeUnit.SECONDS)
                .until(expectedCondition);
    }

    private DesiredCapabilities getDesiredCapabilities(){
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setBrowserName("firefox");
        caps.setJavascriptEnabled(true);
        caps.setVersion("ANY");
        caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
        return caps;
    }

    public void get(String s) {
        driver.get(s);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        return waitr.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public WebElement findElement(By by) {
        return waitr.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    
    public void close() {
        driver.close();
    }

    
    public void quit() {
        driver.quit();
    }

    
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    
    public Navigation navigate() {
        return driver.navigate();
    }

    public Options manage() {
        return driver.manage();
    }

    public WebDriver getDriver(){
        return driver;
    }
}
