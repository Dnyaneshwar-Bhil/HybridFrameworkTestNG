package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviderClass;

/*

Data is valid   --    login success     --   test pass  --- logout
                --    login fail        --   test fail

Data is invalid  --    login success     --   test fail --- logout
                 --    login fail        --   test fail

*/
public class TC_003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviderClass.class, groups = "master")
    public void verify_loginDDT(String email, String password, String exp) {
        logger.info("******** Start  TC_003_LoginDDT ********");

        // Home Page
        HomePage homePage = new HomePage(driver);
        homePage.setClickOnMyAccount();
        homePage.setClickOnLogin();

        // Login Page
        LoginPage loginPage = new LoginPage(driver);

        // fetching data from data provider file
        loginPage.setEmailAddress(email);
        loginPage.setTxtPassword(password);
        loginPage.clickOnLogin();


        logger.info("******** My Account page Display ********");

        // MyAccount Page
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        boolean targetPage = myAccountPage.checkMyAccountPageExits();

        /*
        Data is valid   --    login success     --   test pass  --- logout
                        --    login fail        --   test fail

        Data is invalid  --    login success     --   test fail --- logout
                         --    login fail        --   test fail

        * */
        if (exp.equalsIgnoreCase("valid")) {

            if (targetPage == true) {
                myAccountPage.clickOnBtnLogout();
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }

        if (exp.equalsIgnoreCase("invalid")) {

            if (targetPage == true) {
                myAccountPage.clickOnBtnLogout();
                Assert.assertTrue(false);
            } else {
                Assert.assertTrue(true);
            }
        }


        logger.info("******** Finished  TC_003_LoginDDT ********");
    }

}
