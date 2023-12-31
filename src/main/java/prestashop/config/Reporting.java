package prestashop.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reporting {

    public static final ThreadLocal<ExtentTest> threadReport = new ThreadLocal<>();

    static ExtentHtmlReporter htmlReport;
    protected static ExtentReports report;

    public void intializeReport() {
        String reportpath = "result/report.html";
        htmlReport = new ExtentHtmlReporter(reportpath);
        htmlReport.config().setDocumentTitle("MultiThreading Report");
        htmlReport.config().setReportName("Automation Report");

        report = new ExtentReports();
        report.attachReporter(htmlReport);

    }

    public void generateReport() {
        report.flush();
    }
}
