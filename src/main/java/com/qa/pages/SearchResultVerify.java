package com.qa.pages;

import com.qa.webdriver.QAFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;


/**
 * Created by Robbie on 9/23/2016.
 */
public class SearchResultVerify<T extends SearchResult> extends QAFactory {

    T page;

    public SearchResultVerify(T page){
        this.page = page;
    }

    /**
     * Verify that there are search results matching the search string
     * @param searchStr
     * @return
     */
    public SearchResult isResultsPresent(String searchStr){
        assertThat("Search results contains one or more matching", page.getElements("result.links", searchStr).size(), greaterThan(0));
        return page;
    }
}
