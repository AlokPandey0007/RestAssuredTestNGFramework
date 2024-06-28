package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.RestClient;
import base.RestObject;
import base.TestBase;
import endpoints.APIEndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.TestDataUtils;
import utils.TestLogger;

public class DeleteAPITest extends TestBase{
	
	
	public  DeleteAPITest() {
		restClient.set( new RestClient(requestSpecification.get()));
	}
	
	@Test(dependsOnGroups ={"createBooking"},priority=4)
	public void verifyTheDeleteBookingAPI()
	{
		String methodName=new Object() {}.getClass().getEnclosingMethod().getName();
		extentTest.set(extent.createTest(methodName));
		
		//logging
		TestLogger.info("Running test case :"+methodName);
		
		Response APIresponse;
		RestClient client = restClient.get();


		
		Map<String,Object> pathParams= new HashMap<String, Object>();
		if(testContext.sharedData.get("bookingId")==null)
		{
			pathParams.put("id", 2322);
		}
		else {
		pathParams.put("id", testContext.sharedData.get("bookingId"));
		}
		
		RestObject restObjects= new RestObject();
		restObjects.setPathParams(pathParams);

		responseLocal.set(client.delete(APIEndPoints.DELETE_DELETEBOOKING,restObjects));
		
		APIresponse=responseLocal.get();
		
		//validating the response code should be 200
		APIresponse.then().log().all().assertThat().statusCode(201);

		
	

	}

}
