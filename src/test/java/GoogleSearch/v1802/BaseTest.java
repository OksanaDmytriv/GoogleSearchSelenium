package GoogleSearch.v1802;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {

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
}
