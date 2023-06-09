package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;


@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {
    @Test
    @Tag("newTag")
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")})
    @DisplayName("Compare article title with expected one")
    @Description("We open 'Java Object-oriented programming language' article and make sure the title is expected")
    @Step("Starting test testCompareArticleTitle")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

     //   articlePageObject.takeScreenShot("article_page");

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")})
    @DisplayName("Swipe article to the footer")
    @Description("We open an article and swipe it to the footer")
    @Step("Starting test testSwipeArticle")
    @Severity(value = SeverityLevel.MINOR)
    public void testSwipeArticle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        if (Platform.getInstance().isAndroid()){
            searchPageObject.clickByArticleWithSubstring("Search Wikipedia");
        } else {
            searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        }

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.getArticleTitle();
        articlePageObject.swipeToFooter();
    }
}
