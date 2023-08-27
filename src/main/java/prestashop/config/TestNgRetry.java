package prestashop.config;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestNgRetry implements IRetryAnalyzer {
    private int countAttempt = 1;
    private int maxAttempt = 4;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (countAttempt < maxAttempt) {
                countAttempt++;
                return true;
            }
        }
        return false;
    }
}
