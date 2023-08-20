package prestashop.config;

import com.aventstack.extentreports.ExtentTest;

public class LoggerFactory {
    public static final ThreadLocal<ExtentTest> logger = new ThreadLocal<>();

    private LoggerFactory() {

    }
}
