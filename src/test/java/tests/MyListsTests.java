package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;

import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";
    private static final String
            login = "Denis1990Glumov",
            password = "8*qB2VdK!np+n.G";
    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        System.out.println(article_title);

        if(Platform.getInstance().isAndroid()) {
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

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isIOS()) {
            searchPageObject.clickCancelSearch();
        }

        navigationUI.openNavigation();
        navigationUI.clickMyList();

        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openFolderByName(name_of_folder);
        }

        if (Platform.getInstance().isIOS()) {
            myListPageObject.clickCloseButton();
        }

        //Thread.sleep(2000);

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            myListPageObject.swipeByArticleToDelete(article_title);
        }

        if (Platform.getInstance().isIOS()) {
            myListPageObject.deleteArticleThroughButtons(article_title);
        }
    }
}
