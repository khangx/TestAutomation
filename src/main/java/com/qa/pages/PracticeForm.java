package com.qa.pages;

import com.qa.pages.practiceFormSkeleton.Fieldset;
import com.qa.webdriver.QAFactory;
import org.openqa.selenium.WebDriver;

/**
 * Created by Robbie on 9/23/2016.
 */
public class PracticeForm extends QAFactory implements Fieldset{

    public PracticeForm(WebDriver driver){
        super(driver);
    }
    public PracticeFormVerify verify(){ return new PracticeFormVerify(this);}

    public PracticeForm fillInFieldSet(String textarea, String checkBoxOption, String radioButton, String dropDownSelection, BY byType, String date, String verificationValErr){
        sendKeys(getElement("textarea"), textarea);
        checkBox(getElement("checkbox.option", checkBoxOption));
        checkBox(getElement("radiobox.option", radioButton));
        selectBy(getElement("dropdown.menu"), dropDownSelection, byType);

        if(date.equalsIgnoreCase("today")){
            getElement("calendar.input").click();
            getElement("calendar.today").click();
        }

        sendKeys(getElement("verification.input"), verificationValErr);
        getElement("verification.fieldset").click();
        return this;
    }

    public PracticeForm submitValidVerification(String value){
        sendKeys(getElement("verification.input"), value);
        getElement("verification.fieldset").click();
        return this;
    }
}
