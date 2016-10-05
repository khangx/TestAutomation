package com.qa.pages;

import com.qa.webdriver.QAFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


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
        assertThat("Trailers are present", page.isElementPresent("trailers"), is(true));
        return page;
    }
}
