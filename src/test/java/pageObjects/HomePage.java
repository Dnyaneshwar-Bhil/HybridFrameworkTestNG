package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/*
Note :

- Every page object class contains three 3 thing
    1. constructor
    2. Locator
    3. Actions methods
*/
public class HomePage extends BasePage {

    // 1. constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // 2. Locators
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement clickOnMyAccount;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Register']")
    WebElement clickOnRegister;

    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement clickOnLogin;

    // 3. Action methods
    public void setClickOnMyAccount() {
        clickOnMyAccount.click();
    }

    public void setClickOnRegister() {
        clickOnRegister.click();
    }

    public void setClickOnLogin() {
        clickOnLogin.click();
    }


}
