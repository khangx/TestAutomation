package com.qa.pages.staticmenu;

import com.qa.pages.SearchResult;
import com.qa.webdriver.QAFactory;
import org.openqa.selenium.WebElement;

/**
 * Created by Robbie on 10/5/2016.
 */
public class ConsumerNavigation {

    /**
     * Mouse over the movies menu and wait for the submenu
     * @param qaFactory - referenced child of parent
     * @param menu - Consumer Menu to mouse over
     * @param subMenu - Sub menu of parent drop down
     */
    public static void openMenu(QAFactory qaFactory, final WebElement menu, final WebElement subMenu){
        qaFactory.mouseOverAndWaitFor(menu, subMenu);
    }

    public static SearchResult search(QAFactory qaFactory, WebElement searchField, String movieTitle, WebElement submitBtn){
        qaFactory.sendKeys(searchField, movieTitle);
        qaFactory.click(submitBtn);
        return qaFactory.getPage(SearchResult.class);
    }
}
