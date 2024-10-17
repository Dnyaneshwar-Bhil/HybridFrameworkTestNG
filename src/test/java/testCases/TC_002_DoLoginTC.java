package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;


public class TC_002_DoLoginTC extends BaseClass {

    @Test(groups = {"regression", "master"})
    public void verifyLoginTestCase()  {
        logger.info("******** Start TC_002_DoLoginTC ********");

        HomePage homePage = new HomePage(driver);
        homePage.setClickOnMyAccount();
        homePage.setClickOnLogin();

        LoginPage loginPage = new LoginPage(driver);

        //Reading properties file
        loginPage.setEmailAddress(properties.getProperty("email"));
        loginPage.setTxtPassword(properties.getProperty("password"));

        loginPage.clickOnLogin();

        // check login successful
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        boolean myMyAccountPage = myAccountPage.checkMyAccountPageExits();
        if (myMyAccountPage) {
            logger.info("******** Login Test Case Successfully Execute ********");

            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(true);
        }
    }
}
