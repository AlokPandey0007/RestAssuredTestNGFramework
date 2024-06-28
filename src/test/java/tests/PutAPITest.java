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
import pojo.Bookingdates;
import pojo.CreateBookingPost;
import utils.TestDataUtils;
import utils.TestLogger;

public class PutAPITest extends TestBase{

	public  PutAPITest() {
		restClient.set( new RestClient(requestSpecification.get()));
	}
	
	@Test(dependsOnGroups ={"createBooking"},priority=2)
	public void verifyTheUpdateBookingAPI()
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
		String checkInDate=testdata.getStartDate();
		String checkOutDate=testdata.getEndDate(checkInDate);
		String additionalNeed=testdata.getAdditionalNeeds();
		Integer price=testdata.getPrice();
		Boolean depositePaid=testdata.getBooleanFlag();
		
		//creating request body
		
		Bookingdates dates = new Bookingdates();
		String startDate=testdata.getStartDate();
		dates.setCheckin(checkInDate);
		dates.setCheckout(checkOutDate);
		
		CreateBookingPost payload = new CreateBookingPost();
		payload.setFirstname(firstName);
		payload.setLastname(lastName);
		payload.setAdditionalneeds(additionalNeed);
		payload.setTotalprice(price);
		payload.setDepositpaid(depositePaid);
		payload.setBookingdates(dates);
		
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
		restObjects.setBody(payload);
		responseLocal.set(client.put(APIEndPoints.PUT_UPDATEBOOKING,restObjects));
		
		APIresponse=responseLocal.get();
		JsonPath responseBody=APIresponse.then().log().all().extract().response().jsonPath();
	
		//Asserting
		SoftAssert softAssert= new SoftAssert();
		softAssert.assertEquals(firstName, responseBody.get("firstname"));
		softAssert.assertEquals(lastName, responseBody.get("lastname"));
		softAssert.assertEquals(price, responseBody.get("totalprice"));
		softAssert.assertEquals(depositePaid, responseBody.get("depositpaid"));
		softAssert.assertEquals(checkInDate, responseBody.get("bookingdates.checkin"));
		softAssert.assertEquals(checkOutDate, responseBody.get("bookingdates.checkout"));
		softAssert.assertEquals(additionalNeed, responseBody.get("additionalneeds"));
		
		softAssert.assertAll();
		
	
	}
	
}
