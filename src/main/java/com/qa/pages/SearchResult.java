package com.qa.pages;

import com.qa.webdriver.QAFactory;
import org.openqa.selenium.WebDriver;

import static com.qa.listener.QALogger.logStep;

/**
 * Created by Robbie on 10/5/2016.
 */
public class SearchResult extends QAFactory {
    public SearchResult(WebDriver driver){
        super(driver);
    }

    public SearchResultVerify verify(){ return new SearchResultVerify(this);}

    /**
     * Chose a movie link to click
     * @param movie - Movie title
     * @return - Movie page
     */
    public Movie chooseMovie(String movie){
        logStep(this, String.format("Click on movie title %s", movie));
        click(getElement("result.link", movie));
        return getPage(Movie.class);
    }
}
