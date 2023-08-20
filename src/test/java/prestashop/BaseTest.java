package prestashop;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import prestashop.config.DriverFactory;
import prestashop.config.LoggerFactory;
import prestashop.config.Reporting;
import prestashop.pages.MainPage;
import prestashop.util.CreateLink;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class BaseTest extends Reporting {
    private static final Map<Long, MainPage> pageInstances = new ConcurrentHashMap<>();

    @BeforeSuite
    public void startReporting() {
        intializeReport();
    }

    @AfterSuite
    public void endReporting() {
        generateReport();
    }

    @BeforeMethod
    public void beforeTest(Method result) {
        DriverFactory.getInstance().getDriver().get("https://demo.prestashop.com/#/en/front");
        ExtentTest test = report.createTest(result.getName());
        LoggerFactory.logger.set(test);
        pageInstances.putIfAbsent(Thread.currentThread().getId(), new MainPage());
    }

    @AfterMethod
    public void afterTest(Method result) {
        String status = LoggerFactory.logger.get().getStatus().toString();
        System.out.println(status);
        if (status.equals("fail")) {
            captureScreenshot();
        }

        DriverFactory.getInstance().removeDriver();
        LoggerFactory.logger.remove();
        pageInstances.remove(Thread.currentThread().getId());
    }

    public MainPage getMainPage(){
        return pageInstances.get(Thread.currentThread().getId());
    }

    private synchronized void captureScreenshot() {
        String path = System.getProperty("user.dir") + "/result/screeshot" + Math.random() + ".jpg";
        File sourceFile = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourceFile, new File(path));
            CreateLink mark = new CreateLink(path, "Screenshot");
            LoggerFactory.logger.get().log(Status.FAIL, mark);

        } catch (IOException e) {
            LoggerFactory.logger.get().fail("Unable to take screenshot");
        }
    }
}
