package tests;

import static org.hamcrest.Matchers.containsString;

import java.awt.print.Book;
import java.time.LocalDate;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.RestClient;
import base.RestObject;
import base.TestBase;
import base.TestContext;
import endpoints.APIEndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import pojo.Bookingdates;
import pojo.CreateBookingPost;
import utils.TestDataUtils;
import utils.TestLogger;

public class PostAPITest extends TestBase{
	
	public  PostAPITest() {
		restClient.set( new RestClient(requestSpecification.get()));
	}
	
	@Test(groups = "createBooking",priority=0)
	public void verifyTheCreateBookingAPI()
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
		
		
		RestObject restObjects= new RestObject();
		restObjects.setBody(payload);
		responseLocal.set(client.post(APIEndPoints.POST_CREATEBOOKING,restObjects));
		
		APIresponse=responseLocal.get();
		JsonPath responseBody=APIresponse.then().log().all().extract().response().jsonPath();
		APIresponse.then().assertThat().statusCode(200).body(containsString("bookingid"));
		
		testContext.sharedData.put("bookingId", responseBody.getString("bookingid"));
		
	
		//Asserting
		SoftAssert softAssert= new SoftAssert();
		softAssert.assertEquals(firstName, responseBody.get("booking.firstname"));
		softAssert.assertEquals(lastName, responseBody.get("booking.lastname"));
		softAssert.assertEquals(price, responseBody.get("booking.totalprice"));
		softAssert.assertEquals(depositePaid, responseBody.get("booking.depositpaid"));
		softAssert.assertEquals(checkInDate, responseBody.get("booking.bookingdates.checkin"));
		softAssert.assertEquals(checkOutDate, responseBody.get("booking.bookingdates.checkout"));
		softAssert.assertEquals(additionalNeed, responseBody.get("booking.additionalneeds"));
		
		softAssert.assertAll();
		
		//setting booking id in test context to use in other test cases
		
		
		
	}

}
