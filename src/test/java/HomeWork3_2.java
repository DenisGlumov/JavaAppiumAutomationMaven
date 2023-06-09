import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class HomeWork3_2 extends CoreTestCase {

    @Test
    public void testAssertTitleWithoutWait() throws InterruptedException {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.checkArticleWithoutWait();
    }
}
