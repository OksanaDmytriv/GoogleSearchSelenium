package GoogleSearch.v1802_2;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class GoogleSearchTest {

    static WebDriver driver;
    static ExamplePage page;
    static WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
        page = new ExamplePage(driver);
        wait = new WebDriverWait(driver, 6);
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }

    @Test
    public void testSearchText() {

        openGoogle();
        searchText("Selenium automates browsers");
        assertResultsSize(10);
        assertFirstResult("Selenium - Web Browser Automation");
        followNthLink(0);
        assertOpenedURL("Selenium - Web Browser Automation");
    }

    private void openGoogle() {
        driver.get("http://google.com/ncr");
    }

    private void searchText(String searchText) {
        page.searchField.sendKeys(searchText + Keys.ENTER);
    }

    private void assertResultsSize(int expectedSize) {
        wait.until(Helpers.sizeOf(page.results, expectedSize));
    }

    private void assertFirstResult(String expectedText) {
        wait.until(textToBePresentInElementLocated(By.cssSelector(".srg>.g:nth-child(1)"), expectedText));
    }

    private void followNthLink(int resultLink) {
        page.results.get(resultLink).findElement(By.cssSelector(".r>a")).sendKeys(Keys.ENTER);
    }

    private void assertOpenedURL(String expectedTitle) {
        //этот метод так же работает с параметром: http://www.seleniumhq.org/
        // wait.until(urlContains(expectedTitle));

        wait.until(titleContains(expectedTitle));
    }
}

