import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

@Epic("Tests for my list")
public class HomeWork3_1 extends CoreTestCase {

    private static final String
            login = "Denis1990Glumov",
            password = "8*qB2VdK!np+n.G";

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article"),@Feature(value = "MyList")})
    @DisplayName("Saving two articles")
    @Description("saving two articles, deleting one of the saved ones, checking that the second one remains")
    @Step("Starting test testSaveArticlesToMyList")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveArticlesToMyList() throws InterruptedException {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(login, password);
            auth.submitForm();

            articlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login",
                    article_title,
                    articlePageObject.getArticleTitle());

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
        } else if (Platform.getInstance().isIOS()) {
            searchPageObject.clickByArticleWithSubstring("Appium");
            articlePageObject.addArticlesToMySaved();
        } else {
            searchPageObject.clickByArticleWithSubstring("utomation for Apps");
            articlePageObject.addArticlesToMySaved();
        }


        if (Platform.getInstance().isAndroid()) {
            articlePageObject.waitForTitleElement();
            // String article_title_second = articlePageObject.getArticleTitle();
            articlePageObject.addSecondArticleToMyList();
            articlePageObject.closeArticle();
        }

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isIOS()) {
            articlePageObject.closeArticle();
            searchPageObject.clickCancelSearch();
        }

        navigationUI.openNavigation();
        navigationUI.clickMyList();

        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openFolderByName(name_of_folder);
        }

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            myListPageObject.swipeByArticleToDelete(article_title);
        }

        if (Platform.getInstance().isIOS()) {
            myListPageObject.clickCloseButton();
            myListPageObject.deleteArticleThroughButtons(article_title);
        }

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
            Assert.assertTrue(element.isDisplayed());
        }
    }
}
