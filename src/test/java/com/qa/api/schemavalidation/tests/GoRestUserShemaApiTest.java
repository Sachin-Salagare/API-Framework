package com.qa.api.schemavalidation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.gorestpojo.GoRestUser;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoRestUserShemaApiTest extends BaseTest {
	@Test
	public void productApiTest() {
//		RestAssured.given()
//		           .baseUri("https://gorest.co.in")
//		           .header("Authorization" , "Bearer a30d9bb0e65279e927bdaf3abeaf5b3b32c065968b5782722e5b008284cad520")
//		           .when()
//		           .get("/public/v2/users/7527463")
//		           .then()
//		           .assertThat()
//		           .statusCode(200)
//		           .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/GoRestUser-schema.json"));//6th 35.00
		
	    //1. POST: create user 
			 GoRestUser user= GoRestUser.builder()
			      .name("ApiTest")
			      .email(StringUtility.getRandomEmailId())
			      .gender("male")
			      .status("active")
			      .build();
			 Response response =restclient.post(BASE_URL_GOREST,"/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
			 Assert.assertEquals(response.statusCode(), 201);
			 String userId=response.jsonPath().getString("id");
			 System.out.println("user id is==>"+ userId);
		
	Response response1= restclient.get(BASE_URL_GOREST,"/public/v2/users/"+userId , null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	Assert.assertEquals( SchemaValidator.validateSchema(response1, "schema/GoRestUser-schema.json"),true);
	}
}
