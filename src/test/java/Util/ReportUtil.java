package Util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportUtil {


    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;


    public  ExtentReports createTestReport() {
        htmlReporter  = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setDocumentTitle("Automation Report");

        htmlReporter.config().setReportName("report");

        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        htmlReporter.config().setTheme(Theme.STANDARD);
        return extent;

    }
}