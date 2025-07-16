package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestNGListener implements ITestListener{

	    private static ExtentReports extent = ExtentManager.getExtentReports();
	    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	    @Override
	    public void onTestStart(ITestResult result) {
	        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
	        test.set(extentTest);
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        test.get().pass("Test passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        test.get().fail(result.getThrowable()); // Log exception/stack trace
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        test.get().skip("Test skipped");
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        extent.flush();
	    }
}
