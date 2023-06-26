package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTION_BUTTON,
            OPTION_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            MENU_APPEARED_CHANGE_LANGUAGE,
            MENU_APPEARED_SHARE_LINK,
            MENU_APPEARED_ADD_TO_READING_LIST,
            MENU_APPEARED_FIND_IN_PAGE,
            MENU_APPEARED_SIMILAR_PAGES,
            MENU_APPEARED_FRONT_END_THEM,
            MY_CREATE_LIST,
            CLOSE_ARTICLE_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting for title on the article page")
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    @Step("Get article title")
    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        screenshot(this.takeScreenShot("article_title"));
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }

    }

    @Step("Swiping to footer on article page")
    public void swipeToFooter() {

        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    80
            );
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTitleElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    80);
        } else {
            this.scrollWebWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    80
            );
        }
    }

    @Step("Adding the article to my list")
    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTION_BUTTON,
                "Cannot find button to open article option",
                5
        );

        this.waitForMenuAppeared();

        this.waitForElementAndClick(
                OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of article folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    @Step("Removing the article from saved if it has been added")
    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1
            );
            this.waitForElementPresent(
                    OPTION_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from this list before"
            );

        }
    }


    @Step("Closing the article")
    public void closeArticle() {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X link",
                    5
            );
        } else {
            System.out.println("Method closeArticle() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Waiting for the whole list to appear")
    public void waitForMenuAppeared() {
        waitForElementPresent(
                MENU_APPEARED_CHANGE_LANGUAGE,
                "Cannot find text 'Change language'",
                5
        );
        waitForElementPresent(
                MENU_APPEARED_SHARE_LINK,
                "Cannot find text 'Share link'",
                5
        );
        waitForElementPresent(
                MENU_APPEARED_ADD_TO_READING_LIST,
                "Cannot find text 'Add to reading list'",
                5
        );
        waitForElementPresent(
                MENU_APPEARED_FIND_IN_PAGE,
                "Cannot find text 'Find in page'",
                5
        );
        waitForElementPresent(
                MENU_APPEARED_SIMILAR_PAGES,
                "Cannot find text 'Similar pages'",
                5
        );
        waitForElementPresent(
                MENU_APPEARED_FRONT_END_THEM,
                "Cannot find text 'Font and theme'",
                5
        );
    }

    @Step("Waiting for the whole list to appear for second adding")
    public void waitForMenuSecondAppeared() {
        waitForElementPresent(
                MENU_APPEARED_CHANGE_LANGUAGE,
                "Cannot find text 'Change language'",
                5
        );
        waitForElementPresent(
                MENU_APPEARED_SHARE_LINK,
                "Cannot find text 'Share link'",
                5
        );
        waitForElementPresent(
                MENU_APPEARED_ADD_TO_READING_LIST,
                "Cannot find text 'Add to reading list'",
                5
        );
        waitForElementPresent(
                MENU_APPEARED_FIND_IN_PAGE,
                "Cannot find text 'Find in page'",
                5
        );
        waitForElementPresent(
                MENU_APPEARED_FRONT_END_THEM,
                "Cannot find text 'Font and theme'",
                5
        );
    }

    @Step("Second adding the article to my list")
    public void addSecondArticleToMyList() {
        this.waitForElementAndClick(
                OPTION_BUTTON,
                "Cannot find button to open article option",
                5
        );

        this.waitForMenuSecondAppeared();

        this.waitForElementAndClick(
                OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                MY_CREATE_LIST,
                "Cannot find 'My list'",
                5
        );
    }

    @Step("Adding the article to my saved articles")
    public void addArticlesToMySaved() {
        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    @Step("Check article when we don't have an expectation")
    public void checkArticleWithoutWait() {
        this.assertElementPresent(
                TITLE,
                "Cannot find Article"
        );
    }
}
