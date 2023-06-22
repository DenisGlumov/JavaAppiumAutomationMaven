package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
       // OPTION_ADD_TO_MY_LIST_BUTTON = "css:#page-actions #ca-watch";
        OPTION_ADD_TO_MY_LIST_BUTTON = "css:#page-actions-watch #ca-watch.mw-ui-icon-wikimedia-star-base20";
        MY_CREATE_LIST = "id:org.wikipedia:id/item_container";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions-watch #ca-watch.mw-ui-icon-wikimedia-unStar-progressive";
        // CLOSE_ARTICLE_BUTTON = "id:Search";
        //ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
    }

    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
