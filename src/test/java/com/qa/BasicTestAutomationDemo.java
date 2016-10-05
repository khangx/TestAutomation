package com.qa;

import com.qa.annotations.KnownBugs;
import com.qa.annotations.TestReference;
import com.qa.pages.ImdbHome;
import org.testng.annotations.Test;

/**
 * Created by Robbie on 9/23/2016.
 *
 */
public class BasicTestAutomationDemo extends BaseFactory {

    @Test(description = "Go to www.imdb.com and mouse over the Movies, TV & Shows. Verify that the sub menu becomes visible.")
    @TestReference(reference = {"015","030"})
    @KnownBugs(bugs = {"QAA01","QAA02"})
    public void movies_InTheaters(){
        getPage(ImdbHome.class)
                .verify().areTrailersPresent()
                .goToMovies_InTheaters()
                .verify().isInTheatersHeaderLoaded("In Theaters");
    }
}
