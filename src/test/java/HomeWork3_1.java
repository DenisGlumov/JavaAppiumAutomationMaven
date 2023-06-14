import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class HomeWork3_1 extends CoreTestCase {
    @Test
    public void testSaveArticlesToMyList() throws InterruptedException {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        articlePageObject.closeArticle();

        if (Platform.getInstance().isIOS()) {
            searchPageObject.clearTheSearchLine();
        }

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        if (Platform.getInstance().isAndroid()) {
            searchPageObject.clickByArticleWithSubstring("Search Wikipedia");
        } else {
            searchPageObject.clickByArticleWithSubstring("Appium");
            articlePageObject.addArticlesToMySaved();
        }

        articlePageObject.closeArticle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.waitForTitleElement();
            String article_title_second = articlePageObject.getArticleTitle();
            articlePageObject.addSecondArticleToMyList();
            articlePageObject.closeArticle();
        }

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        searchPageObject.clickCancelSearch();
        navigationUI.clickMyList();

        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openFolderByName(name_of_folder);
            myListPageObject.swipeByArticleToDelete(article_title);
        }

        if (Platform.getInstance().isIOS()) {
            myListPageObject.clickCloseButton();
            myListPageObject.deleteArticleThroughButtons(article_title);
        }
//        assertEquals(
//                "Article title have been change after screen orientation",
//                "Appium",
//                article_title_second
//        );

        if (Platform.getInstance().isAndroid()) {
            myListPageObject.articleSelection();
            articlePageObject.waitForTitleElement();
            String article_text = articlePageObject.getArticleTitle();

            Assert.assertEquals(
                    "Article title have been change after screen orientation",
                    "Appium",
                    article_text
            );
        } else {
            WebElement element = myListPageObject.findElementOnMyList();
            assertTrue(element.isDisplayed());
        }


    }
}
