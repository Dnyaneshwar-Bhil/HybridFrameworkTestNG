package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;
    public ExtentTest test;

    String reportName = "";

    @Override
    public void onStart(ITestContext testContext) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".//reports//" + reportName);

        sparkReporter.config().setDocumentTitle("OpenCart App Automation Report");
        sparkReporter.config().setReportName("OpenCart App Functional Testing");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        extentReports.setSystemInfo("Application", "OpenCart");
        extentReports.setSystemInfo("ModuleName", "Admin");
        extentReports.setSystemInfo("SubModuleName", "Customer");
        extentReports.setSystemInfo("UserName", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("Tester Name", "Dnyaneshwar Bhil");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extentReports.setSystemInfo("Operating System", os);
        extentReports.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extentReports.setSystemInfo("Include Groups", String.valueOf(includedGroups));
        }

    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("onTestStart called");
    }

    // Invoked each time a test succeeds.
    @Override
    public void onTestSuccess(ITestResult result) {
        // Create a new entry in the report
        test = extentReports.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());   //Display groups into the reports

        test.log(Status.PASS, result.getName() + "got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Create a new entry in the report
        test = extentReports.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());   //Display groups into the reports

        test.log(Status.FAIL, result.getName() + "got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imagePath = new BaseClass().captureTheScreenShot(result.getName());
            // Add screenshot to the report
            test.addScreenCaptureFromPath("E:\\IdeaProjects\\HybridFrameworkTestNG\\screenshots\\verify_registration_2024.10.16.12.44.00.jpeg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Invoked each time a test is skipped.
    @Override
    public void onTestSkipped(ITestResult result) {
        // Create a new entry in the report
        test = extentReports.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());   //Display groups into the reports

        test.log(Status.SKIP, result.getName() + "got Skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        // must // only this method update the report for all methods in report
        extentReports.flush();

        // open the report automatically when test fails
//        String pathOfExtentReport = ".//reports//" + reportName;
//        File file = new File(pathOfExtentReport);
//        try {
//            Desktop.getDesktop().browse(file.toURI());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }
}
