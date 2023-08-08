package prestashop;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import prestashop.config.Factory;
import prestashop.config.Reporting;
import prestashop.config.SoftAssertion;
import prestashop.pages.MainPage;
import prestashop.util.CreateLink;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest extends Reporting {
    protected final SoftAssertion softAssertion = new SoftAssertion();
    protected MainPage mainPage;

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
        Factory.getInstance().getDriver().get("https://demo.prestashop.com/#/en/front");
        ExtentTest test = report.createTest(result.getName());
        Factory.logger.set(test);
        mainPage = new MainPage();
    }

    @AfterMethod
    public void afterTest() {
        String status = Factory.logger.get().getStatus().toString();
        System.out.println(status);
        if (status.equals("fail")) {
            captureScreenshot();
        }

        Factory.getInstance().removeDriver();
        Factory.logger.remove();
    }

    private synchronized void captureScreenshot() {
        String path = System.getProperty("user.dir")+"/result/screeshot" + Math.random() + ".jpg";
        File sourceFile = ((TakesScreenshot) Factory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourceFile, new File(path));
            CreateLink mark = new CreateLink(path, "Screenshot");
            Factory.logger.get().log(Status.FAIL, mark);

        } catch (IOException e) {
            Factory.logger.get().fail("Unable to take screenshot");
        }
    }
}
