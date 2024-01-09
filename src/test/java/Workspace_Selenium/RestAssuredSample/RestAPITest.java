package Workspace_Selenium.RestAssuredSample;

import io.restassured.RestAssured;
import io.restassured.path.json.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import files.APIPayload;

public class RestAPITest {

	public static void main(String[] args)
	{
		//given - All input details
		//when - Submit the API - resource, http method
		//Then - validate the response
		
		RestAssured.baseURI = "https://www.rahulshettyacademy.com";
		String mResponse = given().log().all().queryParam("key", "qaclick123")
			.header("Content-Type","application/json")
			.body(APIPayload.AddPlaceJsonPaylod())
		.when().post("maps/api/place/add/json")
		//.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"),"status",equalTo("OK"))
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"),"status",equalTo("OK"))
		.header("server", "Apache/2.4.52 (Ubuntu)")
		.extract().response().asString();
		
		JsonPath mPayloadReq = new JsonPath(APIPayload.AddPlaceJsonPaylod());
		float mLangitude = mPayloadReq.get("location.lng"); // Dot used after location as lng is under location
		float mLitude = mPayloadReq.get("location.lat"); // Dot used after location as lat is under location
		String mPhoneNumber = mPayloadReq.getString("phone_number");
		String mName = mPayloadReq.getString("name");
		
		System.out.println("Input Json Langitude "+mLangitude);
		System.out.println("Input Json Litude "+mLitude);
		System.out.println("Input Json Phone number "+mPhoneNumber);
		System.out.println("Input Json Name "+mName);
		
		//Extract Place ID from response
		JsonPath mJson = new JsonPath(mResponse);
		String mPlaceID = mJson.getString("place_id");
		String mRef = mJson.getString("reference");
		
		System.out.println("Response PlaceID "+mPlaceID);
		System.out.println("Response Reference "+mRef);
	}
}