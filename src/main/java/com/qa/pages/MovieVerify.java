package com.qa.pages;

import com.qa.webdriver.QAFactory;

import static com.qa.listener.QALogger.logStep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Created by Robbie on 9/23/2016.
 */
public class MovieVerify<T extends Movie> extends QAFactory {

    T page;

    public MovieVerify(T page){
        this.page = page;
    }

    /**
     * Verify that the Movie page is loaded
     * @param movieTitle - Movie title
     * @return Movie page
     */
    public Movie isMoviePageLoaded(String movieTitle){
        logStep(page, String.format("Verify that the Movies page is loaded and movie Title \"%s\" is present", movieTitle));
        assertThat("Movie title is "+movieTitle, page.getDisplayText("movie.title"), is(movieTitle));
        return page;
    }
}
