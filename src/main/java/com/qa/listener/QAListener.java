package com.qa.listener;

import com.qa.BaseFactory;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.uncommons.reportng.HTMLReporter;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Robbie on 10/5/2016.
 */
public class QAListener extends HTMLReporter implements ITestListener  {
    private Logger LOGGER = LogManager.getLogger(QAListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info("Starting Test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("Test Success: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info("Test Failure: " + result.getName());
        getFailureSS(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.info("Test Skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        LOGGER.info("Starting Test Suite: " + context.getSuite().getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LOGGER.info("Test Suite Finished: " + context.getSuite().getName());
    }

    private void getFailureSS(ITestResult iTestResult){
        File ss = ((TakesScreenshot)((BaseFactory)iTestResult.getInstance()).getDriver()).getScreenshotAs(OutputType.FILE);
        String testName = iTestResult.getMethod().getMethodName();
        Long date = Calendar.getInstance().getTime().getTime();
        String ssName = String.format("%s_%s.png", testName, date);
        File ssLocation = new File(System.getProperty("user.dir")+"/target/html/ss/"+ssName);
        try {
            FileUtils.copyFile(ss, ssLocation);
            Reporter.log(String.format("<div class='ss'><a href=\"ss/%s\">%s</a></div>", ssLocation.getName(), ssLocation.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
