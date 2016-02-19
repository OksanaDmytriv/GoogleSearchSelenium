package GoogleSearch.v1802;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ExamplePage {

    static WebDriver driver;

        @FindBy(name = "q")
        WebElement searchField;

        @FindBy(css = ".srg>.g")
        List<WebElement> results;

        public ExamplePage(WebDriver driver) {
            PageFactory.initElements(driver, this);
            this.driver=driver;
        }
    }

