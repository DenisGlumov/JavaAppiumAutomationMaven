import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class HomeWork2_2 extends CoreTestCase {
    @Test
    public void testHomeWork2_2() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("football");

        int elementCount = searchPageObject.getAmountOfFoundArticles();
        System.out.println(elementCount);
        assertTrue(
                "We found too few results",
                elementCount > 0
        );
        searchPageObject.clearTheSearchLine();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }
}
