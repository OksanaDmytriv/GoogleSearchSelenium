package GoogleSearch.v1802_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class Helpers {
    public static ExpectedCondition<Boolean> sizeOf(final List<WebElement> results, final int expectedSize) {
        return new ExpectedCondition<Boolean>() {
            private int listSize;

            public Boolean apply(WebDriver webDriver) {
                listSize = results.size();
                return listSize == expectedSize;
            }

            public String toString() {
                return String.format("\nsize of list: %s\n to be: %s\n while actual size is: %s\n", results, expectedSize, listSize);

            }
        };
    }
}
