package com.qa.pages;

import com.qa.webdriver.QAFactory;

import static com.qa.listener.QALogger.logStep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


/**
 * Created by Robbie on 9/23/2016.
 */
public class InTheatersVerify<T extends InTheaters> extends QAFactory {

    T page;

    public InTheatersVerify(T page){
        this.page = page;
    }

    /**
     * Assert that the In Theaters page has a title matching the parameter
     * @param inTheaters - In Theater header title
     * @return - InTheaters
     */
    public InTheaters isInTheatersHeaderLoaded(String inTheaters){
        logStep(page, String.format("Verify that the Theaters header is \"%s\"", inTheaters));
        assertThat("In Theaters header is present", page.getElement("header.inTheaters").getText(), containsString(inTheaters));
        return page;
    }

}
