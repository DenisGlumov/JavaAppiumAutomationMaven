import lib.CoreTestCase;
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

        articlePageObject.addArticleToMyList(name_of_folder);
        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Search Wikipedia");

        articlePageObject.waitForTitleElement();
        String article_title_second = articlePageObject.getArticleTitle();
        articlePageObject.addSecondArticleToMyList();
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyList();

        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
        myListPageObject.openFolderByName(name_of_folder);
        myListPageObject.swipeByArticleToDelete(article_title);

        assertEquals(
                "Article title have been change after screen orientation",
                "Appium",
                article_title_second
        );

        myListPageObject.articleSelection();

        articlePageObject.waitForTitleElement();
        String article_text = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been change after screen orientation",
                "Appium",
                article_text
        );
    }
}
