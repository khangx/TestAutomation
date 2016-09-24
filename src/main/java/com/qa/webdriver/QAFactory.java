package com.qa.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Robbie on 9/23/2016.
 */
public abstract class QAFactory extends QADriver {
    private final Integer softCheckTimeOut = 5;
    public ResourceBundle rb;
    protected ResourceBundle config = ResourceBundle.getBundle("config");

    public enum BY{
        VALUE, TEXT
    }

    public QAFactory(){}
    public QAFactory(WebDriver driver){
        this.driver = driver;
    }

    public void selectBy(WebElement menuElement, String value, BY option){
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

    public void checkBox(WebElement box){
        if(!box.isSelected()){
            box.click();
        }
    }

    public void sendKeys(WebElement webElement, String value){
        webElement.clear();
        webElement.sendKeys(value);
    }

    public WebElement getElement(String locator, String... params){
       return findElement(locatorBuilder(locator, params));
    }

    public List<WebElement> getElements(String element, String... params){
        for(String param : params){
            element = element.replaceFirst("%s", param);
        }

        return findElements(locatorBuilder(element));
    }


    public Boolean isElementPresent(String locator, String... params){
        try {
            waiter(ExpectedConditions.presenceOfElementLocated(locatorBuilder(locator,params)), softCheckTimeOut);
            return true;
        }catch (Exception e){
            return false;
        }
    }

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

    public String getDisplayText(String locator, String... params){
        return getElement(locator, params).getText();
    }

    public String getElementAttribute(String locator, String attribute, String... params){
        return getElement(locator, params).getAttribute(attribute);
    }

    private By locatorBuilder(String path, String... params){
        rb = ResourceBundle.getBundle(this.getClass().getName(), Locale.getDefault(),this.getClass().getClassLoader());
        By by = null;
        String[] locator = rb.getString(path).split(";");

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

    public <T>T getPage(Class<T> clazz){

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
