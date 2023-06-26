import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

@Epic("Tests for search")
public class HomeWork2_3 extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Checking words in search")
    //@Description("Check if the search works and then cancel the search")
    @Step("Starting test testHomeWork2_3")
    @Severity(value = SeverityLevel.NORMAL)
    public void testHomeWork2_3() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("java");
        searchPageObject.checkSearchWordInSearchResult("Java");
    }
}
