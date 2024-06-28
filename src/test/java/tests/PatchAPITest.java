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

public class PatchAPITest extends TestBase{
	
	
	public  PatchAPITest() {
		restClient.set( new RestClient(requestSpecification.get()));
	}
	
	@Test(dependsOnGroups ={"createBooking"},priority=1)
	public void verifyThePartialUpdateBookingAPI()
	{
	
		String methodName=new Object() {}.getClass().getEnclosingMethod().getName();
		TestDataUtils testdata= new TestDataUtils();
		extentTest.set(extent.createTest(methodName));
		
		//logging
		TestLogger.info("Running test case :"+methodName);
		
		Response APIresponse;
		RestClient client = restClient.get();
		
		//Geeting test data
		String firstName=testdata.getFirstName();
		String lastName=testdata.getFirstName();

		//creating request body
		
		Map<String,String> body= new HashMap<String, String>();
		body.put("firstname", firstName);
		body.put("lastname", lastName);
		
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
		restObjects.setBody(body);
		responseLocal.set(client.patch(APIEndPoints.PATCH_UPDATEBOOKING,restObjects));
		
		APIresponse=responseLocal.get();
		JsonPath responseBody=APIresponse.then().log().all().extract().response().jsonPath();

		
		//Asserting
		SoftAssert softAssert= new SoftAssert();
		softAssert.assertEquals(firstName, responseBody.get("firstname"));
		softAssert.assertEquals(lastName, responseBody.get("lastname"));
		softAssert.assertAll();

	}

}
