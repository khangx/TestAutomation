package com.qa;

import com.qa.annotations.KnownBugs;
import com.qa.annotations.TestReference;
import com.qa.pages.PracticeForm;
import org.testng.annotations.Test;

/**
 * Created by Robbie on 9/23/2016.
 *
 */
public class BasicTestAutomationDemo extends BaseFactory {

    @Test(description = "Visit the test site, and test Fieldset and Verification forms functionality.")
    @TestReference(reference = {"015","30"})
    @KnownBugs(bugs = {"QAA01","QAA02"})
    public void testFieldSetAndVerification(){
        String textareaContent = "Hello QAE";
        String checkBox = "Option 2";
        String radioButton = "Option 1";
        String dropDownSelection = "Option 2";
        String date = "Today";
        String verificationErrInput = "123";
        String verificationValidInput = "12";
        String expectedVerificationErrMsg = "Please enter no more than 2 characters.";

        getPage(PracticeForm.class)
                .fillInFieldSet(textareaContent, checkBox, radioButton, dropDownSelection, BY.TEXT, date, verificationErrInput)
                .verify().isSubmitErrorMsgVisible(expectedVerificationErrMsg)
                .submitValidVerification(verificationValidInput)
                .verify().isSubmitErrorMsgHidden();
    }
}
