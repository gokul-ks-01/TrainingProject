package Util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportUtil {

    private final ExtentHtmlReporter htmlReporter;
    private final ExtentReports extent;

    public ReportUtil(String reportFileName) {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + reportFileName);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        configureHtmlReporter();
    }

    private void configureHtmlReporter() {
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("report");
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        htmlReporter.config().setTheme(Theme.STANDARD);
    }

    public ExtentReports createTestReport() {
        return extent;
    }
}
