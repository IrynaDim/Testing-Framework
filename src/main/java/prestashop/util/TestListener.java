package prestashop.util;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    private static final String REPORT_PATH = "test-report.txt";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onTestStart(ITestResult result) {
        writeReport("Time start " + DATE_FORMAT.format(new Date()), result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        writeReport("PASSED with time end " + DATE_FORMAT.format(new Date()), result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        writeReport("FAILED with time end " + DATE_FORMAT.format(new Date()), result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        writeReport("SKIPPED", result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    private synchronized void writeReport(String text, String testName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_PATH, true))) {
            writer.write(String.format("Test '%s' %s with thread id %s%n", testName, text, Thread.currentThread().getId()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
