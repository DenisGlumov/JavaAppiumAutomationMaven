import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

@Epic("Tests for search")
public class HomeWork2_1 extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Checking the search and result")
    @Step("Starting test testHomeWork2_1")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testHomeWork2_1() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.assertSearchLineContainsText("Search Wikipedia");
    }
}
