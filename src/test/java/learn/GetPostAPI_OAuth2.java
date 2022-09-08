package learn;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
public class GetPostAPI_OAuth2 {
	public String getAccessToken() {
		String aToken = "";
		String cliendID = "1ar0cs57e1k46919a78qlqs4cb";
		String clientSecret = "1llkotvdre5v92nd3s4up20pum3a7775atpiha12sqk0c7l2f72k";
		String OAuthScope = "rs/read rs/write";
		String grantType = "client_credentials";
		Response response = RestAssured.given()
		.auth().preemptive()
		.basic(cliendID, clientSecret)
		.param("grant_type", grantType)
		.param("scope", OAuthScope)
		.log().all()
		.post("https://oath.auth.us-east-1.amazoncognito.com/oauth2/token");
		aToken = response.getBody().path("access_token");	
		//System.out.println("================= TokenValue : " + aToken);
		return (aToken);
	}
      
	@Test(priority = 1)
	public void test() {
		String nameofCurrentClass = new Object() {
        }.getClass().getEnclosingClass().getName();
        String nameofCurrentMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println("Class - > method:    " + nameofCurrentClass + " -> " + nameofCurrentMethod);
	String accessToken = getAccessToken();
        //System.out.println(" accessToken : " + accessToken);
		Response response = RestAssured.given()
		.header("authorization", " Bearer " + accessToken)
		.contentType("application/json")
		.get("https://5pi8ssjjq8.execute-api.us-east-1.amazonaws.com/prd/?name=Sharath&city=Houston");
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
		String nameofCurrentClass = new Object() {
        }.getClass().getEnclosingClass().getName();
        String nameofCurrentMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println("Class - > method:    " + nameofCurrentClass + " -> " + nameofCurrentMethod);
	String accessToken = getAccessToken();
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
		Response response = RestAssured.given()
		.header("authorization", " Bearer " + accessToken)
		.contentType("application/json")
		.body(json)
		.when()
		.post("https://5pi8ssjjq8.execute-api.us-east-1.amazonaws.com/prd/");
		response.then().log().all();
		//System.out.println("====================================== " + response.getBody().jsonPath().get("message"));
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getBody().jsonPath().get("message"), "Your ID is created  with the name Aarush Kammari ");
	}

}