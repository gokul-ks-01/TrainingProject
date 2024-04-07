package Util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ReportUtil implements ITestListener {

    private static final ExtentReports extentReports;

    static {
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Test Automation Results");
        htmlReporter.config().setTheme(Theme.STANDARD);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Extent Report - Test Suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Extent Report - Test Suite finished: " + context.getName());
        extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Extent Report - Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Extent Report - Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Extent Report - Test Failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Extent Report - Test Skipped: " + result.getName());
    }

    // Other methods from ITestListener if needed

    public static ExtentReports getExtentReports() {
        return extentReports;
    }
}
