package com.nikhil.testComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.nikhil.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>(); //use for making Thread safe and avoid concurrency issue where 2 objects are trying to override same obj.
    @Override
    public void onStart(ITestContext context) {
        // Code to execute before any test starts
        extent.createTest("Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Code to execute after all tests are executed
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Called when a test starts
        test = extent.createTest(result.getMethod().getMethodName());
        extenttest.set(test); //will create a unique thread ID(For each test case) and map to test Object.
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Called when a test succeeds
    	extenttest.get().log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Called when a test fails
    	extenttest.get().log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        extenttest.get().fail(result.getThrowable());
        String filePath = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
            extenttest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Called when a test is skipped
        //test.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
    	extenttest.get().log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
    	extenttest.get().fail(result.getThrowable());
        String filePath = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
            extenttest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Called when a test fails but is within success percentage
    	extenttest.get().log(Status.FAIL, "Test Failed but within success percentage: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // Called when a test fails due to timeout
    	extenttest.get().log(Status.FAIL, "Test Failed due to timeout: " + result.getMethod().getMethodName());
        onTestFailure(result);
    }
}
