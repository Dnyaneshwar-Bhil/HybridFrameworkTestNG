package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage extends BasePage {

    // 1. Constructor
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // 2. Locators
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtFirstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txtTelephone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txtConfirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chPrivacyPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
    WebElement txtConfirmMessage;


    // 3. Actions methods


    public void setTxtFirstName(String firstName) {
        txtFirstName.sendKeys(firstName);
    }

    public void setTxtLastName(String lastName) {
        txtLastName.sendKeys(lastName);
    }

    public void setTxtEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void setTelephone(String telephone) {
        txtTelephone.sendKeys(telephone);
    }

    public void setTxtPassword(String password) {
        txtPassword.sendKeys(password);
    }

    public void setTxtConfirmPassword(String confirmPassword) {
        txtConfirmPassword.sendKeys(confirmPassword);
    }

    public void clickOnCheckBoxPrivacyPolicy() {
        chPrivacyPolicy.click();

    }

    public void clickOnBtnRegister() {
        // Approach 1
        btnContinue.click();

       /* // Approach 2
        btnContinue.submit();

        // Approach 3
        Actions actions = new Actions(driver);
        actions.moveToElement(btnContinue).click().perform();

        // Approach 4
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("argument[0].click();", btnContinue);

        // Approach 5
        btnContinue.sendKeys(Keys.RETURN);

        // Approach 6
        WebDriverWait myWait =new WebDriverWait(driver, Duration.ofSeconds(10));
        myWait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();*/
    }


    public String getConfirmationMessage() {
        try {
            return txtConfirmMessage.getText();
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
}
