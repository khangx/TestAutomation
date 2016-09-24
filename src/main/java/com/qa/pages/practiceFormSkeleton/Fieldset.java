package com.qa.pages.practiceFormSkeleton;

import com.qa.pages.PracticeForm;
import com.qa.webdriver.QAFactory;

/**
 * Created by Robbie on 9/23/2016.
 * Skeletal design for Fieldset form to be treated as widget
 */
public interface Fieldset{
    /**
     * Fill in the Fieldset form
     * @param textarea - Textarea content
     * @param checkBoxOption - Check box option
     * @param radioButton - Radio button option
     * @param dropDownSelection - Drop down menu selection option
     * @param byType - enum TYPE <VALUE></VALUE> or <TEXT>visible text</TEXT>
     * @param date - String indicate to select today
     * @param verificationValErr - Invalid number of numeric string
     * @return - PracticeForm
     */
    PracticeForm fillInFieldSet(String textarea, String checkBoxOption, String radioButton, String dropDownSelection, QAFactory.BY byType, String date, String verificationValErr);

    /**
     * Submit a valid verification string
     * @param value - A valid string input
     * @return
     */
    PracticeForm submitValidVerification(String value);
}
