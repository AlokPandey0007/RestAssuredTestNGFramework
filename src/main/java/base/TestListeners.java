package base;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestListeners extends TestBase implements ITestListener{
	
	public  void onTestSuccess(ITestResult result) {
		
		if(result.isSuccess()) {
			extentTest.get().log(Status.PASS, "Test case is passed");
		}
	   
	  }
	
	public  void onTestFailure(ITestResult result) {
		if(!result.isSuccess()) {
			extentTest.get().log(Status.FAIL, "Test case is failed");
		}
	  }

}
