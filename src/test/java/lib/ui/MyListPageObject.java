package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_SELECTION,
            CLOSE_BUTTON,
            BUTTON_EDIT,
            CHOICE_ARTICLE,
            DELETE_ARTICLE,
            MY_SAVE_ARTICLE,
            CHECK_DELETE_ARTICLE,
            REMOVE_FROM_SAVED_BUTTON;

    public MyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Get name folder by xpath")
    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    @Step("Get name button remove by title")
    private static String getRemoveButtonByTitle(String article_title) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    @Step("Save article by xpath")
    private static String getSaveArticleByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    @Step("Waiting and click by folder")
    public void openFolderByName(String name_of_folder) throws InterruptedException {
        Thread.sleep(500);
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        System.out.println(folder_name_xpath);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }

    @Step("Waiting to appear article")
    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSaveArticleByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title", 25);
    }


    @Step("Waiting to disappear article")
    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSaveArticleByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title", 15);
    }

    @Step("Swipe article for delete")
    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSaveArticleByTitle(article_title);

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article");
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            System.out.println("делаем клик на удаление статьи");
            System.out.println(remove_locator);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
        }
        if (Platform.getInstance().isIOS()) {
            this.clickToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }

        if (Platform.getInstance().isMW()) {
            this.waitForElementPresent(CHECK_DELETE_ARTICLE, "Cannot find button Remove", 5);
            driver.navigate().refresh();
            System.out.println("Обновляем страницу");
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }

    @Step("Article selection")
    public void articleSelection() {
        this.waitForElementAndClick(
                ARTICLE_SELECTION,
                "Cannot find 'Appium'",
                5
        );
    }

    @Step("Waiting and click button close")
    public void clickCloseButton() {
        this.waitForElementAndClick(
                CLOSE_BUTTON,
                "Cannot click button 'Close'",
                5
        );
    }

    @Step("Delete article through button")
    public void deleteArticleThroughButtons(String article_title) {
        this.waitForElementAndClick(BUTTON_EDIT, "Cannot find button 'edit'", 5);
        this.waitForElementAndClick(CHOICE_ARTICLE, "Cannot find button 'Choice'", 5);
        this.waitForElementAndClick(DELETE_ARTICLE, "Cannot find button 'Unsave'", 5);
        this.waitForArticleToDisappearByTitle(article_title);
    }

    @Step("Waiting element on my list")
    public WebElement findElementOnMyList() {
        return this.waitForElementPresent(MY_SAVE_ARTICLE, "Cannot find my save Artilce 'Appium'", 5);
    }
}
