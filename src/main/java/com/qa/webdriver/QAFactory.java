package com.qa.webdriver;

import com.qa.listener.LogLevel;
import com.qa.pages.staticmenu.ConsumerNavigation;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static com.qa.listener.QALogger.log;
import static com.qa.qautil.QAUtilities.getByFromWebElement;

/**
 * Created by Robbie on 9/23/2016.
 */
public abstract class QAFactory extends QADriver {
    private final Integer softCheckTimeOut = 5;
    public ResourceBundle rb;
    protected ResourceBundle config = ResourceBundle.getBundle("config");
    private Actions actions;
    private JavascriptExecutor js;

    public enum BY{
        VALUE, TEXT
    }

    public QAFactory(){}

    /**
     * Constructor to initialize Selenium WebDriver and Actions
     * @param driver
     */
    public QAFactory(WebDriver driver){
        this.driver = driver;
        actions = new Actions(driver);
        js = (JavascriptExecutor)driver;
        waitr = new FluentWait(driver)
                .ignoring(NoSuchElementException.class)
                .pollingEvery(800, TimeUnit.MILLISECONDS)
                .withTimeout(20, TimeUnit.SECONDS);
    }

    /**
     * Select by visible text or by option value
     * @param menuElement - Dropdown menu
     * @param value - option value
     * @param option - TEXT or VALUE
     */
    public void selectBy(WebElement menuElement, String value, BY option){
        log(this.getClass(), String.format("Select by %s value %s from %s", option, value, getByFromWebElement(menuElement)));
        Select menu = new Select(menuElement);
        switch(option){
            default:
                throw new IllegalArgumentException("No such method to select by "+option.name());
            case VALUE:
                menu.selectByValue(value);
                break;
            case TEXT:
                menu.selectByVisibleText(value);
                break;
        }
    }

    /**
     * Check an input box if it is not already checked
     * @param box - input checkbox
     */
    public void checkBox(WebElement box){
        log(this, String.format("Check box %s", getByFromWebElement(box)));
        if(!box.isSelected()){
            box.click();
        }
    }

    /**
     * Clear and send text to web element
     * @param webElement - Input or element to send text to
     * @param value - Text to type
     */
    public void sendKeys(WebElement webElement, String value){
        log(this, String.format("Send text %s to %s", value, getByFromWebElement(webElement)));
        webElement.clear();
        webElement.sendKeys(value);
    }

    /**
     * Get a WebElement based on locator and parameters
     * @param locator - Locator variable from resource properties files
     * @param params - Text to replace in the locator
     * @return - WebElement
     */
    public WebElement getElement(String locator, String... params){
       return findElement(locatorBuilder(locator, params));
    }

    /**
     * Get a WebElement based on locator and parameters
     * @param clazz - The next page to expect (Navigation)
     * @param locator - Locator variable from resource properties files
     * @param params - Text to replace in the locator
     * @return - WebElement
     */
    public WebElement getElement(Class clazz, String locator, String... params){
        return findElement(locatorBuilder(locator, params));
    }

    /**
     * Get a list of WebElements
     * @param locator - Locator variable from resource properties files
     * @param params - Text to replace in the locator
     * @return - List<WebElement></WebElement>
     */
    public List<WebElement> getElements(String locator, String... params){
        return findElements(locatorBuilder(locator, params));
    }

    /**
     * Check if an element is present in the HTML DOM
     * @param locator - Locator variable from resource properties files
     * @param params - Text to replace in the locator
     * @return - True if found, else false
     */
    public Boolean isElementPresent(String locator, String... params){
        try {
            waiter(ExpectedConditions.presenceOfElementLocated(locatorBuilder(locator, params)), softCheckTimeOut);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    /**
     * Check if an element is visible
     * @param locator - Locator variable from resource properties files
     * @param params - Text to replace in the locator
     * @return - True if visible, else false
     */
    public Boolean isElementVisible(String locator, String... params){
        try{
            waiter(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver input) {
                    try{
                        getElement(locator, params);
                        return true;
                    }catch (Exception e1){
                        return false;
                    }
                }
            }, softCheckTimeOut);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Get the display text of the WebElement
     * @param locator - Locator variable from resource properties files
     * @param params - Text to replace in the locator
     * @return - Text from WebElement
     */
    public String getDisplayText(String locator, String... params){
        WebElement webElement = getElement(locator, params);
        log(this, "Get display text from "+getByFromWebElement(webElement));
        return webElement.getText();
    }

    /**
     * Get the value of a specified attribute from a WebElement
     * @param locator - Locator variable from resource properties files
     * @param attribute - Specified attribute from WebElement
     * @param params - Text to replace in the locator
     * @return - String value of attribute
     */
    public String getElementAttribute(String locator, String attribute, String... params){
        WebElement webElement = getElement(locator, params);
        log(this, String.format("Get attribute %s from %s", attribute, getByFromWebElement(webElement)));
        return webElement.getAttribute(attribute);
    }

    /**
     * Get the Selenium By based on the properties file locator type
     * @param locatorVar - Locator variable from resource properties files
     * @param params - Text to replace in the locator
     * @return - By.ID, By.className, By.XPATH etc.
     */
    private By locatorBuilder(String locatorVar, String... params){
        rb = ResourceBundle.getBundle(this.getClass().getName(), Locale.getDefault(), this.getClass().getClassLoader());
        By by = null;
        String[] locator;
        try{
            locator = rb.getString(locatorVar).split(";");
        }catch (MissingResourceException e){
            rb = ResourceBundle.getBundle(ConsumerNavigation.class.getName(), Locale.getDefault(), ConsumerNavigation.class.getClassLoader());
            locator = rb.getString(locatorVar).split(";");
        }

        String locatorPath = locator[0];
        String locatorBy = locator[1];

        for(String param : params){
            locatorPath = locatorPath.replaceFirst("%s", param);
        }

        switch (locatorBy){
            case "ID":
                by = By.id(locatorPath);
                break;
            case "CSS":
                by = By.cssSelector(locatorPath);
                break;
            case "CLASSNAME":
                by = By.className(locatorPath);
                break;
            case "LINKTEXT":
                by = By.linkText(locatorPath);
                break;
            case "NAME":
                by = By.name(locatorPath);
                break;
            case "TAGNAME":
                by = By.tagName(locatorPath);
                break;
            case "PARTIAL_LINK_TEXT":
                by = By.partialLinkText(locatorPath);
                break;
            case "XPATH":
                by = By.xpath(locatorPath);
                break;
        }

        return by;
    }

    /**
     * Basic selenium click with added functionality to wait until element is clickable
     * @param webElement
     */
    public void click(WebElement webElement){
        waitr.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    /**
     * Perform mouseover on a WebElement, and wait for an element to become visible
     * @param mouseIn - WebElement to MouseOver
     * @param waitFor - WebElement to wait on
     */
    public void mouseOverAndWaitFor(WebElement mouseIn, WebElement waitFor){
        log(this, String.format("MouseOver %s and wait for %s", getByFromWebElement(mouseIn), getByFromWebElement(waitFor)), LogLevel.FATAL);
        jsExecute("alert('Automation');");
        driver.switchTo().alert().accept();
        actions.moveToElement(mouseIn).build().perform();
        waitr.until(ExpectedConditions.visibilityOf(waitFor));
    }

    /**
     * Execute javascript
     * @param str - JavaScript code
     */
    public void jsExecute(String str){
        log(this, "Executing javascript: "+str);
        js.executeScript(str);
    }

    /**
     * Get the next navigation page object
     * @param clazz
     * @param <T>
     * @return
     */
    public <T>T getPage(Class<T> clazz){
        log(clazz, "Navigate to page: "+clazz.getSimpleName(), LogLevel.DEBUG);
        T page = null;
        try {
            page = clazz.getConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return page;
    }
}
