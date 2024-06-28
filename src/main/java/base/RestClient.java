package base;

import java.util.HashMap;
import java.util.Map.Entry;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;

import groovy.util.MapEntry;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.AuthUtils;
import utils.ConfigReader;
import utils.TestLogger;

public class RestClient extends TestBase {

	RequestSpecification requestSpecification;

	Response response;
	Object[] objects;

	public RestClient(RequestSpecification requestSpecification) {

		this.requestSpecification = requestSpecification;

	}

	public RestClient(RequestSpecification requestSpecification, Object[] objects) {
		this.requestSpecification = requestSpecification;
		this.objects = objects;
	}

	public Response get(String endpoint, RestObject restObjects) {
		// extent report logging
		ExtentTest test = extentTest.get();
		test.log(Status.PASS, "Endpoint is : " + endpoint);

		// log4j logging
		TestLogger.info("GET endpoint - " + endpoint);

		// request specification
		requestSpecification = RestAssured.given().log().all().auth().oauth2(AuthUtils.getAccessToken())
				.header("Content-Type", "application/json");

		// adding path parameter
		if (restObjects.getPathParams().size() > 0) {
			for (Entry<String, Object> item : restObjects.getPathParams().entrySet()) {
				//Adding to test report
				test.log(Status.PASS,
						"Adding path parameters with Key : " + item.getKey() + " and value : " + item.getValue());
				
				//log4j logging
				TestLogger.info("Adding path parameters with Key : " + item.getKey() + " and value : " + item.getValue());
				
				//adding path parameter to request
				requestSpecification.pathParam(item.getKey(), item.getValue());
			}
		}

		// adding query parameter
		if (restObjects.getQueryParams().size() > 0) {
			for (Entry<String, Object> item : restObjects.getQueryParams().entrySet()) {
				//Adding to test report
				test.log(Status.PASS,
						"Adding query parameters with Key : " + item.getKey() + " and value : " + item.getValue());
				
				//log4j logging
				TestLogger.info("Adding query parameters with Key : " + item.getKey() + " and value : " + item.getValue());
				
				//adding query parameter to request
				requestSpecification.queryParam(item.getKey(), item.getValue());
			}
		}

		//sending get request
		TestLogger.info("Sending get request");
		response = requestSpecification.when().get(endpoint);

		//Test Reporting
		test.log(Status.PASS, "API Response code is : " + response.then().extract().statusCode());
		test.log(Status.PASS, "API Response body is : " + response.then().extract().body().asString());
		
		//log4j logging
		TestLogger.info("API Response code is : " + response.then().extract().statusCode());
		TestLogger.info("API Response body is : " + response.then().extract().body().asString());

		return response;

	}

	public Response get(String endpoint) {
		// extent report logging
		ExtentTest test = extentTest.get();
		test.log(Status.PASS, "Endpoint is : " + endpoint);

		// log4j logging
		TestLogger.info("GET endpoint - " + endpoint);

		// request specification
		requestSpecification = RestAssured.given().auth().oauth2(AuthUtils.getAccessToken()).header("Content-Type",
				"application/json");

		//sending get request
		TestLogger.info("Sending get request");
		response = requestSpecification.when().get(endpoint);

		//Test Report logging
		test.log(Status.PASS, "API Response code is : " + response.then().extract().statusCode());
		test.log(Status.PASS, "API Response body is : " + response.then().extract().body().asString());
		
		//log4j logging
		TestLogger.info("API Response code is : " + response.then().extract().statusCode());
		TestLogger.info("API Response body is : " + response.then().extract().body().asString());

		return response;

	}

	// Post Request
	public Response post(String endpoint, RestObject restObjects) {

		// extent report logging
		ExtentTest test = extentTest.get();
		test.log(Status.PASS, "Endpoint is : " + endpoint);

		// log4j logging
		TestLogger.info("POST endpoint - " + endpoint);

		// request specification
		requestSpecification = RestAssured.given().log().all().auth().oauth2(AuthUtils.getAccessToken())
				.header("Content-Type", "application/json");

		// adding body to request
		if (restObjects.getBody() != null) {
			requestSpecification.body(restObjects.getBody());
			test.log(Status.PASS, "Body is : " + restObjects.getBody().toString());
		}

		// sending post request
		TestLogger.info("Sending put request");
		response = requestSpecification.when().post(endpoint);

		//Test Report logging
		test.log(Status.PASS, "API Response code is : " + response.then().extract().statusCode());
		test.log(Status.PASS, "API Response body is : " + response.then().extract().body().asString());
		
		//log4j logging
		TestLogger.info("API Response code is : " + response.then().extract().statusCode());
		TestLogger.info("API Response body is : " + response.then().extract().body().asString());
		
		return response;

	}

	// put request
	public Response put(String endpoint, RestObject restObjects) {

		// extent report logging
		ExtentTest test = extentTest.get();
		test.log(Status.PASS, "Endpoint is : " + endpoint);

		// log4j logging
		TestLogger.info("PUT endpoint - " + endpoint);

		// request specification
		ConfigReader config = new ConfigReader();
		requestSpecification = RestAssured.given().log().all().cookie("token", AuthUtils.getAccessToken())
				.header("Content-Type", "application/json");

		// adding path parameter
		if (restObjects.getPathParams().size() > 0) {
			for (Entry<String, Object> item : restObjects.getPathParams().entrySet()) {
				test.log(Status.PASS,
						"Adding path parameters with Key : " + item.getKey() + " and value : " + item.getValue());
				requestSpecification.pathParam(item.getKey(), item.getValue());
			}
		}
		// adding body to request
		if (restObjects.getBody() != null) {
			requestSpecification.body(restObjects.getBody());
			test.log(Status.PASS, "Body is : " + restObjects.getBody().toString());
		}

		// sending put request
		TestLogger.info("Sending put request");
		response = requestSpecification.when().put(endpoint);

		//Test Report logging
		test.log(Status.PASS, "API Response code is : " + response.then().extract().statusCode());
		test.log(Status.PASS, "API Response body is : " + response.then().extract().body().asString());
		
		return response;

	}

	// PATCH request
	public Response patch(String endpoint, RestObject restObjects) {

		// extent report logging
		ExtentTest test = extentTest.get();
		test.log(Status.PASS, "Endpoint is : " + endpoint);

		// log4j logging
		TestLogger.info("PATCH endpoint - " + endpoint);

		// request specification
		requestSpecification = RestAssured.given().log().all().cookie("token", AuthUtils.getAccessToken())
				.header("Content-Type", "application/json");

		// adding path parameter
		if (restObjects.getPathParams().size() > 0) {
			for (Entry<String, Object> item : restObjects.getPathParams().entrySet()) {
				test.log(Status.PASS,
						"Adding path parameters with Key : " + item.getKey() + " and value : " + item.getValue());
				requestSpecification.pathParam(item.getKey(), item.getValue());
			}
		}
		// adding body to request
		if (restObjects.getBody() != null) {
			requestSpecification.body(restObjects.getBody());
			test.log(Status.PASS, "Body is : " + restObjects.getBody().toString());
		}

		// sending patch request
		TestLogger.info("Sending patch request");
		response = requestSpecification.when().patch(endpoint);

		//Test Report logging
		test.log(Status.PASS, "API Response code is : " + response.then().extract().statusCode());
		test.log(Status.PASS, "API Response body is : " + response.then().extract().body().asString());
		
		
		//log4j logging
		TestLogger.info("API Response code is : " + response.then().extract().statusCode());
		TestLogger.info("API Response body is : " + response.then().extract().body().asString());
		
		return response;

	}

	// delete request
	public Response delete(String endpoint, RestObject restObjects) {

		// extent report logging
		ExtentTest test = extentTest.get();
		test.log(Status.PASS, "Endpoint is : " + endpoint);

		// log4j logging
		TestLogger.info("PATCH endpoint - " + endpoint);

		// request specification
		requestSpecification = RestAssured.given().log().all().cookie("token", AuthUtils.getAccessToken())
				.header("Content-Type", "application/json");

		// adding path parameter
		if (restObjects.getPathParams().size() > 0) {
			for (Entry<String, Object> item : restObjects.getPathParams().entrySet()) {
				test.log(Status.PASS,
						"Adding path parameters with Key : " + item.getKey() + " and value : " + item.getValue());
				requestSpecification.pathParam(item.getKey(), item.getValue());
			}
		}

		// sending delete request
		TestLogger.info("Sending delete request");
		response = requestSpecification.when().delete(endpoint);

		//Test Report logging
		test.log(Status.PASS, "API Response code is : " + response.then().extract().statusCode());
		test.log(Status.PASS, "API Response body is : " + response.then().extract().body().asString());
		
		//log4j logging
		TestLogger.info("API Response code is : " + response.then().extract().statusCode());
		TestLogger.info("API Response body is : " + response.then().extract().body().asString());
		
		return response;
	}
}
