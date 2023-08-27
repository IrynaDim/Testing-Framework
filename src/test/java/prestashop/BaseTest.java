package prestashop;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import prestashop.config.Driver;
import prestashop.config.Reporting;
import prestashop.config.TestNgRetry;
import prestashop.model.Link;
import prestashop.pages.mainPage.MainPageAction;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class BaseTest extends Reporting {
    private static final Map<Long, MainPageAction> pageInstances = new ConcurrentHashMap<>();
    private final String startPage = "https://demo.prestashop.com/#/en/front";

    @BeforeSuite
    public void start(ITestContext iTestContext) {
        intializeReport();
        Arrays.stream(iTestContext.getAllTestMethods()).forEach(x -> x.setRetryAnalyzerClass(TestNgRetry.class));
    }

    @AfterSuite
    public void endReporting() {
        generateReport();
    }

    @BeforeMethod
    public void beforeTest(Method result) {
        Driver.getInstance().getDriver().get(startPage);
        ExtentTest test = report.createTest(result.getName());
        Reporting.threadReport.set(test);
        pageInstances.putIfAbsent(Thread.currentThread().getId(), new MainPageAction());
    }

    @AfterMethod
    public void afterTest(Method result) {
        String status = Reporting.threadReport.get().getStatus().toString();
        if (status.equals("fail")) {
            captureScreenshot();
        }

        Driver.getInstance().removeDriver();
        Reporting.threadReport.remove();
        pageInstances.remove(Thread.currentThread().getId());
    }

    public MainPageAction getMainPageAction() {
        return pageInstances.get(Thread.currentThread().getId());
    }

    private synchronized void captureScreenshot() {
        String path = System.getProperty("user.dir") + "/result/screeshot" + Math.random() + ".jpg";
        File sourceFile = ((TakesScreenshot) Driver.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourceFile, new File(path));
            Link mark = new Link(path, "Screenshot");
            Reporting.threadReport.get().log(Status.FAIL, mark);

        } catch (IOException e) {
            Reporting.threadReport.get().fail("Unable to take screenshot");
        }
    }
}
