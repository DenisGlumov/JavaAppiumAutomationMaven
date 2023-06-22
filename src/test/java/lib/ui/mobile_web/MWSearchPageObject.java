package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:div>button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        //SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCollectionView//XCUIElementTypeImage";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_RESULT_LIST = "xpath://XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeStaticText[@visible='true'][1]";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
        CLEAR_SEARCH_LINE = "id:Search Wikipedia";
    }
    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
