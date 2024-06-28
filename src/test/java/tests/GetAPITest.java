package tests;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import base.RestClient;
import base.RestObject;
import base.TestBase;
import endpoints.APIEndPoints;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.TestDataUtils;
import utils.TestLogger;

import static org.hamcrest.Matchers.*;

public class GetAPITest extends TestBase{
	
	public  GetAPITest() {
		restClient.set( new RestClient(requestSpecification.get()));
	}
	
	@Test(priority =0)
	public void verifyThatGetBookingIdsAPIResponseIsNotNull()
	{
		
		extentTest.set(extent.createTest(new Object() {}.getClass().getEnclosingMethod().getName()));
		Response APIresponse;
		RestClient client = restClient.get();
		
		responseLocal.set(client.get(APIEndPoints.GET_BOOOKING_IDS));
		APIresponse=responseLocal.get();
		APIresponse.then().assertThat().statusCode(200).body(containsString("bookingid"));
	}
	
	@Test(priority =1)
	public void verifyThatGetBookingIdAPIResponseIsNotNull()
	{
		String methodName=new Object() {}.getClass().getEnclosingMethod().getName();
		TestDataUtils testdata= new TestDataUtils();
		extentTest.set(extent.createTest(methodName));
		
		//logging
		TestLogger.info("Running test case :"+methodName);
		
		Response APIresponse;
		RestClient client = restClient.get();
		
		//adding path param to restobject
		RestObject restObj = new RestObject();
		HashMap<String, Object> pathParams= new HashMap<String, Object>();
		pathParams.put("id", testContext.sharedData.get("bookingId"));
		restObj.setPathParams(pathParams);
		
		//sending get request
		responseLocal.set(client.get(APIEndPoints.GET_BOOOKING_ID,restObj));
		APIresponse=responseLocal.get();
		APIresponse.then().log().all().assertThat().statusCode(200).body(containsString("firstname"));
	}

}
