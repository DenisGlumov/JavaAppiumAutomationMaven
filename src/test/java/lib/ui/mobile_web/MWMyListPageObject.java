package lib.ui.mobile_web;

import lib.ui.MainPageObject;
import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject {
    static {
        CLOSE_BUTTON = "id:Close";
        //ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
        ARTICLE_SELECTION = "id:org.wikipedia:id/page_list_item_title";
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(),'{TITLE}')]/../../a[contains(@class,'watched')]";
        CHECK_DELETE_ARTICLE = "xpath://a[@title='Add this page to your watchlist']";
        BUTTON_EDIT = "id:Edit";
        CHOICE_ARTICLE = "id:Java (programming language)";
        DELETE_ARTICLE = "xpath://XCUIElementTypeButton[@name='Unsave']";
        MY_SAVE_ARTICLE = "id:Automation for Apps";
    }

    public MWMyListPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
