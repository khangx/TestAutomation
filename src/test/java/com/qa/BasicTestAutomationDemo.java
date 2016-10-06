package com.qa;

import com.qa.annotations.KnownBugs;
import com.qa.annotations.TestReference;
import com.qa.pages.ImdbHome;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Robbie on 9/23/2016.
 *
 */
public class BasicTestAutomationDemo extends BaseFactory {



    @Test(description = "Go to www.imdb.com and mouse over the Movies, TV & Shows. Verify that the sub menu becomes visible.")
    @TestReference(reference = {"015","030"})
    @KnownBugs(bugs = {"QAA01","QAA02"})
    @Parameters({"theaterLink"})
    public void movies_InTheaters(String theaterLink){
        getPage(ImdbHome.class)
                .verify().areTrailersPresent()
                .goToMovies_InTheaters()
                .verify().isInTheatersHeaderLoaded(theaterLink);
    }

    @Test(description = "Go to www.imdb.com and search for BatMan vs.Robin. Verify that movie titled \"BatMan vs.Robin\" is present in search results")
    @TestReference(reference = {"016","040"})
    @KnownBugs(bugs = {"QAA03","QAA04"})
    @Parameters({"searchTitle", "videoResult","movieTitle"})
    public void searchMovie(String searchTitle, String videoResult, String movieTitle){
        getPage(ImdbHome.class)
                .searchFor(searchTitle)
                .verify().isResultsPresent(searchTitle)
                .chooseMovie(videoResult)
                .verify().isMoviePageLoaded(movieTitle);
    }
    @Test(description = "Go to www.imdb.com and search for BatMan vs.Robin. Verify that movie titled \"BatMan vs.Robin\" is present in search results")
    @TestReference(reference = {"016","040"})
    @KnownBugs(bugs = {"QAA03","QAA04"})
    @Parameters({"searchTitle", "videoResult","movieTitle"})
    public void searchMovieOnlyVerifyEnd(String searchTitle, String videoResult, String movieTitle){
        getPage(ImdbHome.class)
                .searchFor(searchTitle)
                .chooseMovie(videoResult)
                .verify().isMoviePageLoaded(movieTitle);
    }
}
