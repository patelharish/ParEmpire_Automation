package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getExtentReports() {
		if (extent == null) {
			String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
			String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport_" + timestamp + ".html";
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
			
			// Add encoding for special characters
			sparkReporter.config().setEncoding("utf-8");

			// Attractive configuration
			sparkReporter.config().setTheme(Theme.DARK);
			sparkReporter.config().setDocumentTitle("G3Fashion Test Report");
			sparkReporter.config().setReportName("Automation Test Report");

			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("Tester", "Harish Patel");
		}
		return extent;
	}
}
