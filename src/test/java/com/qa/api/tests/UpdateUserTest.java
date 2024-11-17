package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.gorestpojo.GoRestUser;
import com.qa.api.utils.StringUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserTest extends BaseTest {
	
	@Test 
	  public void updateUserWithBuilder() {
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
		 
	//2. GET: fetch the same user with the user id 
		 Response responseGet =restclient.get(BASE_URL_GOREST,"/public/v2/users/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		 Assert.assertEquals(responseGet.getStatusCode(), 200);
		 Assert.assertEquals(responseGet.jsonPath().getString("id"), userId);
   
   // update the user details using setters
		 user.setStatus("inactive");
		 user.setGender("female");
	////////////arrenge ,act,assert ////pattern	 
    //3. PUT: Update the same user using user id 
		Response responsePut =restclient.put(BASE_URL_GOREST,"/public/v2/users/"+userId, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responsePut.getStatusCode(), 200);
		Assert.assertEquals(responsePut.jsonPath().getString("id"), userId);
		Assert.assertEquals(responsePut.jsonPath().getString("gender"), user.getGender());
		Assert.assertEquals(responsePut.jsonPath().getString("status"), user.getStatus());
		
		  
	  }	

}
