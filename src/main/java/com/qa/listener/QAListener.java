package com.qa.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.uncommons.reportng.HTMLReporter;

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
}
