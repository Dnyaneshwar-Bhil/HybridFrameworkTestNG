package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    WebElement myAccountText;


    @FindBy(xpath = "//a[@class='list-group-item'][text()='Logout']")
    WebElement btnLogout;

    public boolean checkMyAccountPageExits() {
        try {
            return myAccountText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOnBtnLogout() {
        btnLogout.click();
    }

}
