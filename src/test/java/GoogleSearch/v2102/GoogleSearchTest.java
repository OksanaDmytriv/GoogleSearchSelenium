package GoogleSearch.v2102;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static googletest.core.CustomConditions.minimumSizeOf;
import static googletest.core.CustomConditions.sizeOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class GoogleSearchTest {

    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 6);
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }

    @Test
    public void testFollowLink() {
        givenSearchResults("Selenium automates browsers");

        followNthLink(0);
        assertTitleOpenedURL("Selenium - Web Browser Automation");
    }
    @Test
    public void testSearchCommonFlow() {
        givenSearchResults("Selenium automates browsers");
        assertResultsSize(10);
        assertFirstResult("Selenium - Web Browser Automation");
    }

    public List<WebElement> results() {
        return driver.findElements(By.cssSelector(".srg>.g"));
    }

    String results = (".srg>.g");

    private void givenSearchResults(String request) {
        driver.get("https://www.google.com/?gws_rd=ssl#q=".concat(request));
    }

    private void assertResultsSize(int expectedSize) {
        wait.until(sizeOf((By.cssSelector(results)), expectedSize));
    }

    private void assertFirstResult(String expectedText) {
        wait.until(textToBePresentInElementLocated(By.cssSelector(results + ":nth-child(1)"), expectedText));
    }

    private void followNthLink(int index) {
        wait.until(minimumSizeOf((By.cssSelector(results)), index+1));
        results().get(index).findElement(By.cssSelector(".r>a")).click();
    }

    private void assertTitleOpenedURL(String expectedTitle) {
        wait.until(titleContains(expectedTitle));
    }
}

