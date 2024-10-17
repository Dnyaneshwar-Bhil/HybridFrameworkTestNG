package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;

        // Initializes WebElements with @FindBy annotations
        PageFactory.initElements(driver, this);
    }
}
