package learn;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
public class GetPostAPI_NoAuth {
    
	@Test(priority = 1)
	public void test() {
		String nameofCurrentClass = new Object() {
        }.getClass().getEnclosingClass().getName();
        String nameofCurrentMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println("Class - > method:    " + nameofCurrentClass + " -> " + nameofCurrentMethod);

        System.out.println("API Testing Java Class " );
		Response response = RestAssured.given().contentType("application/json").get("https://m4lj6cn48j.execute-api.us-east-1.amazonaws.com/prd/?name=John&city=Seattle");
		response.then().log().all();
		//System.out.println("latest " + response.statusCode());
		//System.out.println("====================================== " + response.getBody().jsonPath().get("message"));
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getBody().jsonPath().get("message"), "Hello John from Seattle.");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}


	@Test(priority = 2)
	//post request to reqres.in api to create user using RestAssured	
	public void postRequest() {
		String nameofCurrentClass = new Object() {
        }.getClass().getEnclosingClass().getName();
        String nameofCurrentMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println("Class - > method:    " + nameofCurrentClass + " -> " + nameofCurrentMethod);

		   // use org.json JSONObject to define your json
		   JSONObject jsonObj = new JSONObject()
		   .put("name","Aarush Kammari")
		   .put("email","aarush@gmail.com");
		   //print json object
		System.out.println(jsonObj);
		//convert your json object to string
		String json = jsonObj.toString();
		//print your json string
		System.out.println(json);
		Response response = RestAssured.given().contentType("application/json").body(json).when().post("https://l0a0ghhng4.execute-api.us-east-1.amazonaws.com/prd");
		response.then().log().all();
		//System.out.println("====================================== " + response.getBody().jsonPath().get("message"));
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getBody().jsonPath().get("message"), "Your ID is created  with the name Aarush Kammari ");
	}

}