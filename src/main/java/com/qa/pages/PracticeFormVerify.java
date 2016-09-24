package com.qa.pages;

import com.qa.pages.practiceFormSkeleton.Verification;
import com.qa.webdriver.QAFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * Created by Robbie on 9/23/2016.
 */
public class PracticeFormVerify<T extends PracticeForm> extends QAFactory implements Verification {

    T page;

    public PracticeFormVerify(T page){
        this.page = page;
    }

    public PracticeForm isSubmitErrorMsgVisible(String message){
        assertThat("Verification Error message is correct", message, is(page.getDisplayText("verification.err.msg")));
        return page;
    }

    public PracticeForm isSubmitErrorMsgHidden(){
        assertThat("Verification Error message is hidden", page.getElement("verification.err.msg").isDisplayed(), is(false));
        return page;
    }
}
