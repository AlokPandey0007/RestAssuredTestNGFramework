package base;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.testng.annotations.BeforeMethod;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Auth;
import utils.AuthUtils;
import utils.ConfigReader;
import utils.TestLogger;
import utils.TestReport;

public class TestBase {
	
	public static ExtentReports extent;
	protected ThreadLocal<RequestSpecification> requestSpecification;
	protected ThreadLocal<ResponseSpecification> responseSpecification;
	protected ThreadLocal<Response> responseLocal;
	public ThreadLocal<RestClient> restClient;
	public static ThreadLocal<ExtentTest> extentTest ;
	public static TestContext testContext;
	//public static ThreadLocal<ExtentTest> extentTest ;

	
	
	public TestBase()
	{
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		TestReport report = new TestReport();
		extent=report.GetReporter();
		extentTest= new ThreadLocal<ExtentTest>();
		requestSpecification=new ThreadLocal<RequestSpecification>();
		responseLocal= new ThreadLocal<Response>();
		responseSpecification= new ThreadLocal<ResponseSpecification>();
		restClient= new ThreadLocal<RestClient>();
		testContext= new TestContext();
	}
	
	@BeforeSuite
	public void BeforeSuite() {
		TestLogger.info("Creating extent report instance");
		extent = new TestReport().GetReporter();
		
	}
	


	@BeforeMethod()
	public void BeforeMethod() {
		
		
	
	}
	

	@AfterMethod
	public void AfterMethod(ITestResult result) {
		
	ResponseSpecification resSpec=	responseSpecification.get();
	

	}


	@AfterSuite
	public void AfterSuite() {
	extent.flush();
	}
	


}
