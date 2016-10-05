package com.qa.pages.staticmenu;

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
    public static void openMenu(QAFactory qaFactory, WebElement menu, WebElement subMenu){
        qaFactory.mouseOverAndWaitFor(menu, subMenu);
    }
}
