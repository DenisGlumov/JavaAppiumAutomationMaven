package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
            SEARH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_RESULT_LIST,
            SEARCH_EMPTY_RESULT_ELEMENT,
            CLEAR_SEARCH_LINE;

    public SearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
    /*TEMPLATE METHODS*/
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATE METHODS*/

    @Step("Initializing the search field")
    public void initSearchInput(){
        this.waitForElementPresent(SEARH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARH_INIT_ELEMENT, "Cannot find and click search init element", 5);
     }

    @Step("Waiting for button to cancel search result")
     public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
     }

    @Step("Waiting for search cancel button to disappear")
    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    @Step("Clicking button o cancel search result")
    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    @Step("Typing '{search_line}' to the search line")
     public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
     }

    @Step("Waiting for search result")
     public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
     }

    @Step("Waiting for search result and select an article by substring in article title")
    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
        System.out.println(search_result_xpath);
    }

    @Step("Getting amount of found articles")
     public int getAmountOfFoundArticles(){
         this.waitForElementPresent(
                 SEARCH_RESULT_ELEMENT,
                 "Cannot find anything by the result",
                 15
         );
         return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
     }

    @Step("Waiting for empty result label")
     public void waitForEmptyResultLabel(){
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);
     }

    @Step("Making sure there are no results for the search")
     public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
     }

    @Step("Making sure there are results for the search")
     public void assertSearchLineContainsText (String search_line_contains){
         this.assertElementHasText(
                 SEARH_INIT_ELEMENT,
                 search_line_contains,
                 "text is different"
         );
     }

    @Step("Clear search line for the next search")
     public void clearTheSearchLine(){
         this.waitForElementAndClear(
                 CLEAR_SEARCH_LINE,
                 "Cannot find search field",
                 5
         );
     }

    @Step("Check result list according to the given text")
     public void checkSearchWordInSearchResult(String name_text_check){
         int elementCount = getAmountOfFoundArticles();
         System.out.println(elementCount);
         List<WebElement> elementSearch = this.getElementSearch(SEARCH_RESULT_LIST);
//         List<WebElement> elementSearch = driver.findElements(SEARCH_RESULT_LIST);

         for (int i = 0; i < elementCount; i++) {
             if (Platform.getInstance().isAndroid()) {
                 System.out.println(elementSearch.get(i).getAttribute("text"));
                 Assert.assertTrue(elementSearch.get(i).getAttribute("text").contains(name_text_check));
             } else {
                 System.out.println(elementSearch.get(i).getAttribute("name"));
                 Assert.assertTrue(elementSearch.get(i).getAttribute("name").contains(name_text_check));
             }


         }
     }
}
