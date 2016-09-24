package com.qa.pages.practiceFormSkeleton;

import com.qa.pages.PracticeForm;

/**
 * Created by Robbie on 9/23/2016.
 */
public interface Verification{
    /**
     * Verify that the error message is shown upon entering an invalid number of numeric chars (more than 2 chars)
     * @param message - Error message expected shown
     * @return PracticeForm
     */
    PracticeForm isSubmitErrorMsgVisible(String message);

    /**
     * Verify that the error message is hidden if a valid number of numeric chars is entered
     * @return PracticeForm
     */
    PracticeForm isSubmitErrorMsgHidden();
}
