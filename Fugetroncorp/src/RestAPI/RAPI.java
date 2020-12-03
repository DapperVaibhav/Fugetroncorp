package RestAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class RAPI {

public static void main(String[] args) {
		
		try {
			
			//ADD
			
      RestAssured.baseURI= "https://rahulshettyacademy.com";
      String response= given().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n" + 
      		"  \"location\": {\r\n" + 
      		"    \"lat\": -38.383494,\r\n" + 
      		"    \"lng\": 33.427362\r\n" + 
      		"  },\r\n" + 
      		"  \"accuracy\": 50,\r\n" + 
      		"  \"name\": \"Frontline house\",\r\n" + 
      		"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
      		"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
      		"  \"types\": [\r\n" + 
      		"    \"shoe park\",\r\n" + 
      		"    \"shop\"\r\n" + 
      		"  ],\r\n" + 
      		"  \"website\": \"http://google.com\",\r\n" + 
      		"  \"language\": \"French-IN\"\r\n" + 
      		"}").when().post("maps/api/place/add/json").then().log().all().assertThat()
      .statusCode(200).extract().response().asString();
      
      System.out.println("Response is "+response);
      
      JsonPath js= new JsonPath(response);
      String placeID=js.get("place_id");
      System.out.println("Place ID is "+placeID);
      
     //PUT
      String newAddress= "Tardeo";
      given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
      .body("{\r\n" + 
      		"\"place_id\":\""+placeID+"\",\r\n" + 
      		"\"address\":\""+newAddress+"\",\r\n" + 
      		"\"key\":\"qaclick123\"\r\n" + 
      		"}\r\n" + 
      		"")
      .when().put("maps/api/place/update/json").then().assertThat().statusCode(200)
      .body("msg",equalTo("Address successfully updated"));
      
    //GET
      
      String GetReponse= given().log().all().queryParam("key", "qaclick123")
      .queryParam("place_id", placeID)
      .when().get("maps/api/place/get/json")
      .then().assertThat().statusCode(200).extract().response().asString();
      
      JsonPath js1= new JsonPath(GetReponse);
      String actualAddress= js1.getString("address");
      
      if(newAddress.contentEquals(actualAddress)) {
    	  System.out.println("Pass");
      }
      
      else {
    	  System.out.println("Fail");
      }
      
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
}
}

