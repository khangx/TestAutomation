package com.qa.pages;

import com.qa.pages.staticmenu.ConsumerNavigation;
import com.qa.webdriver.QAFactory;
import org.openqa.selenium.WebDriver;

/**
 * Created by Robbie on 9/23/2016.
 */
public class ImdbHome extends QAFactory{

    public ImdbHome(WebDriver driver){
        super(driver);
    }

    public ImdbHomeVerify verify(){ return new ImdbHomeVerify(this);}

    /**
     * Mouseover movies and navigate to "In Theaters" link
     * @return - InTheaters
     */
    public InTheaters goToMovies_InTheaters(){
        ConsumerNavigation.openMenu(this, getElement("menu.movies"), getElement("submenu.movies"));
        click(getElement("inTheaters.link"));
        return getPage(InTheaters.class);
    }

    public SearchResult searchFor(String searchStr){
        return ConsumerNavigation.search(this, getElement("search.input"), searchStr, getElement("search.submit.btn"));
    }
}
