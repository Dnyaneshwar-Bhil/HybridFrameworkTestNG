package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC_001_DoRegisterTC extends BaseClass {

    @Test(groups = {"sanity", "master"})
    void verify_registration() {
        logger.info("********** Starting TC_001_DoRegisterTC **********");
        HomePage homePage = new HomePage(driver);

        logger.info("********** Click on Register Button ****************");
        homePage.setClickOnMyAccount();
        homePage.setClickOnRegister();

        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.setTxtFirstName(properties.getProperty("firstName"));
        registrationPage.setTxtLastName(properties.getProperty("lastName"));
        registrationPage.setTxtEmail(properties.getProperty("email"));
        registrationPage.setTelephone(properties.getProperty("telephone"));
        registrationPage.setTxtPassword(properties.getProperty("password"));
        registrationPage.setTxtConfirmPassword(properties.getProperty("confirmPassword"));

        registrationPage.clickOnCheckBoxPrivacyPolicy();

        registrationPage.clickOnBtnRegister();

        String message = registrationPage.getConfirmationMessage();

        if (message.equals("Your Account Has Been Created!")) {
            Assert.assertTrue(true);
        } else {
            logger.error("Test Failed...");
            logger.debug("Debug logs...");
            Assert.assertTrue(false);
        }

    }

}
