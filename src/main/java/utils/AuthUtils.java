package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import pojo.Auth;

public class AuthUtils {
	
	static String token;
	private static String generateAccessToken()
	{
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		ConfigReader config= new ConfigReader();
		String username=config.ReadProperty("username");
		String password=config.ReadProperty("password");
		Auth authbody=new Auth();
		authbody.setUsername(username);
		authbody.setPassword(password);
		JsonPath path=RestAssured.given().contentType(ContentType.JSON).log().all().body(authbody).when().post("/auth").then().log().all().extract().response().jsonPath();
		token= path.get("token");
		return token;
	}
	
	public static String getAccessToken()
	{
		if(token==null) {
			token=generateAccessToken();
		}
		return token;
	}

}
