package googletest.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class CustomConditions {
    public static ExpectedCondition<Boolean> sizeOf(final By elementsLocator, final int expectedSize) {
        return new ExpectedCondition<Boolean>() {
            private int listSize;
            private List<WebElement> results;

            public Boolean apply(WebDriver webDriver) {
                results = webDriver.findElements(elementsLocator);
                listSize = results.size();
                return listSize == expectedSize;
            }

            public String toString() {
                return String.format("\nsize of list: %s\n should be: %s\n while actual size is: %s\n", results, expectedSize, listSize);

            }
        };
    }

        public static ExpectedCondition<Boolean> minimumSizeOf(final By elementsLocator, final int minimumSize) {
            return new ExpectedCondition<Boolean>() {
                private int listSize;
                private List<WebElement> results;

                public Boolean apply(WebDriver webDriver) {
                    results = webDriver.findElements(elementsLocator);
                    listSize = results.size();
                    return listSize >= minimumSize;
                }

                public String toString() {
                    return String.format("\nsize of list: %s\n minimum size should be: %s\n while actual size is: %s\n", results, minimumSize, listSize);

                }
            };
        }

}
