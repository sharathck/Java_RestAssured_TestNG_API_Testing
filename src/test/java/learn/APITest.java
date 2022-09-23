package learn;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
public class APITest {
    
	@Test(priority = 1)
	public void test() {
		System.out.println("[package.]Class.methodName   :   " + this.getClass().getName() + ".'" + new Throwable().getStackTrace()[0].getMethodName()); 
 	
		Response response = RestAssured.given().contentType("application/json").get("https://reqres.in/api/users?page=2");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getBody().jsonPath().get("data[0].first_name"), "Michael");
		Assert.assertEquals(response.getBody().jsonPath().get("data[0].last_name"), "Lawson");
		Assert.assertEquals(response.getBody().jsonPath().get("data[0].email"), "michael.lawson@reqres.in");
		System.out.println("latest " + response.statusCode());
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test(priority = 2)
	//post request to reqres.in api to create user using RestAssured	
	public void postRequest() {
		System.out.println("[package.]Class.methodName   :   " + this.getClass().getName() + ".'" + new Throwable().getStackTrace()[0].getMethodName()); 
		   JSONObject jsonObj = new JSONObject()
		   .put("name","s1harath")
		   .put("job","software engineer");
		   //print json object
		System.out.println(jsonObj);
		//convert your json object to string
		String json = jsonObj.toString();
		//print your json string
		System.out.println(json);
		Response response = RestAssured.given().contentType("application/json").body(json).when().post("https://reqres.in/api/users");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertEquals(response.getBody().jsonPath().get("name"), "s1harath");
//		Assert.assertEquals(response.getBody().jsonPath().get("job"), "leader");
		//Assert.assertEquals(response.getBody().jsonPath().get("id"), 2);
	}

	@Test(priority = 3)
	//put request to reqres.in api to update user using RestAssured
	public void putRequest() {
		System.out.println("[package.]Class.methodName   :   " + this.getClass().getName() + ".'" + new Throwable().getStackTrace()[0].getMethodName()); 
		   JSONObject jsonObj = new JSONObject()
		   .put("name","chandra")
		   .put("job","software engineer");
		   //print json object
		   System.out.println(jsonObj);
		   //convert your json object to string
		   String json = jsonObj.toString();
		   //print your json string
		   System.out.println(json);
		Response response = RestAssured.given().contentType("application/json").body(json).when().put("https://reqres.in/api/users/2");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getBody().jsonPath().get("name"), "chandra");
}
}