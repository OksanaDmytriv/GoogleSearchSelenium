package GoogleSearch.v2002;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    public void testSearchAndFollowLink() {
        openGoogle();
        searchText("Selenium automates browsers");
        followNthLink(10, 0);
        assertTitleOpenedURL("Selenium - Web Browser Automation");
    }
    @Test
    public void testSearchCommonFlow() {

        openGoogle();
        searchText("Selenium automates browsers");
        assertResultsSize(10);
        assertFirstResult("Selenium - Web Browser Automation");
    }

    public List<WebElement> results() {
        return driver.findElements(By.cssSelector(".srg>.g"));
    }

    String results = (".srg>.g");

    private void openGoogle() {
        driver.get("http://google.com/ncr");
    }

    private void searchText(String searchText) {
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys(searchText+Keys.ENTER);
    }

    private void assertResultsSize(int expectedSize) {
        wait.until(sizeOf((By.cssSelector(results)), expectedSize));
    }

    private void assertFirstResult(String expectedText) {
        wait.until(textToBePresentInElementLocated(By.cssSelector(results + ":nth-child(1)"), expectedText));
    }

    private void followNthLink(int minimumSize, int index) {
        wait.until(minimumSizeOf((By.cssSelector(results)), minimumSize));
        results().get(index).findElement(By.cssSelector(".r>a")).click();
    }

    private void assertTitleOpenedURL(String expectedTitle) {
        wait.until(titleContains(expectedTitle));
    }
}

