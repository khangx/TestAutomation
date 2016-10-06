package com.qa.pages;

import com.qa.webdriver.QAFactory;

import static com.qa.listener.QALogger.logStep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Created by Robbie on 9/23/2016.
 */
public class ImdbHomeVerify<T extends ImdbHome> extends QAFactory {

    T page;

    public ImdbHomeVerify(T page){
        this.page = page;
    }

    /**
     * Assert that trailers are present
     * @return
     */
    public ImdbHome areTrailersPresent(){
        logStep(page, "Verify that the trailers are present and visible");
        assertThat("Trailers are present", page.isElementPresent("trailers"), is(equalTo(true)));
        return page;
    }
}
