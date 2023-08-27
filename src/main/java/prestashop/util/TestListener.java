package prestashop.util;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// todo is it ok of using listener?
public class TestListener implements ITestListener {
    private static final String REPORT_PATH = "test-report.txt";

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        writeReport("PASSED", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        writeReport("FAILED", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        writeReport("SKIPPED", result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    private synchronized void writeReport(String status, String testName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_PATH, true))) {
            writer.write(String.format("Test '%s' %s%n", testName, status));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
