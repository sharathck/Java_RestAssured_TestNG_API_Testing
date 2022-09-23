package learn;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
public class GetPostAPI_With_API_Key {
    
	@Test(priority = 1)
	public void test() {
		System.out.println("[package.]Class.methodName   :   " + this.getClass().getName() + ".'" + new Throwable().getStackTrace()[0].getMethodName()); 
		Response response = RestAssured.given()
		.header("x-api-key", "qMBuquaJ9O9zKD9KCgmyh9Pief9uWqJraZP7r37E")
		.contentType("application/json").get("https://7j82vdkse8.execute-api.us-east-1.amazonaws.com/prd/?name=Sharath&city=Houston");
		response.then().log().all();
		//System.out.println("latest " + response.statusCode());
		//System.out.println("====================================== " + response.getBody().jsonPath().get("message"));
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getBody().jsonPath().get("message"), "Hello Sharath from Houston.");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}


	@Test(priority = 2)
	//post request to reqres.in api to create user using RestAssured	
	public void postRequest() {
		System.out.println("[package.]Class.methodName   :   " + this.getClass().getName() + ".'" + new Throwable().getStackTrace()[0].getMethodName()); 
		   JSONObject jsonObj = new JSONObject()
		   .put("name","Aarush Kammari")
		   .put("email","aarush@gmail.com");
		   //print json object
		System.out.println(jsonObj);
		//convert your json object to string
		String json = jsonObj.toString();
		//print your json string
		System.out.println(json);
		Response response = RestAssured.given().header("x-api-key", "h4mtyDyni61as4YzeHxcy4VLiETt0BVP6Is65j8B").contentType("application/json").body(json).when().post("https://0223dw5mj4.execute-api.us-east-1.amazonaws.com/prd");
		response.then().log().all();
		//System.out.println("====================================== " + response.getBody().jsonPath().get("message"));
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getBody().jsonPath().get("message"), "Your ID is created  with the name Aarush Kammari ");
	}

}