import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for mobile app")
public class HomeWork3_3 extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Mobile phone features")})
    @DisplayName("Change orientation for mobile phone")
    @Description("Change orientation on landscape and portrait and make sure the title is expected")
    @Step("Starting test testChangeScreenOrientationOnSearchResults")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testChangeScreenOrientationOnSearchResults() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String title_before_rotation = articlePageObject.getArticleTitle();

        this.rotateScreenLandscape();
        String title_after_rotation = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been change after screen orientation",
                title_before_rotation,
                title_after_rotation
        );
        this.rotateScreenPortrait();
        String title_after_second_rotation = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been change after screen orientation",
                title_before_rotation,
                title_after_second_rotation
        );
    }
}
