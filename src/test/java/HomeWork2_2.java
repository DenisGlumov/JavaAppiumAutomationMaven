import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for search")
public class HomeWork2_2 extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Cancel search")
    @Description("Check if the search works and then cancel the search")
    @Step("Starting test testHomeWork2_2")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testHomeWork2_2() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("football");

        int elementCount = searchPageObject.getAmountOfFoundArticles();
        System.out.println(elementCount);
        Assert.assertEquals("We found too few results",
                true,
                elementCount > 0
        );
        searchPageObject.clearTheSearchLine();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }
}
