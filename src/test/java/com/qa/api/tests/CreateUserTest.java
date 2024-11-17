package com.qa.api.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.gorestpojo.GoRestUser;
import com.qa.api.utils.StringUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class CreateUserTest extends BaseTest{
	
	// this is using lombok builder
  @Test 
  public void creatUser() {
	  
	  GoRestUser user=new GoRestUser(null,"Sachin", StringUtility.getRandomEmailId(), "male", "active");
	 Response response =restclient.post(BASE_URL_GOREST,"/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	 Assert.assertEquals(response.statusCode(), 201);
	 
  }
	// this is using builder pattern
  @Test 
  public void creatUserWithBuilder() {
	  //post
	 GoRestUser user= GoRestUser.builder()
	      .name("Sachin s")
	      .email(StringUtility.getRandomEmailId())
	      .gender("male")
	      .status("active")
	      .build();
	 Response response =restclient.post(BASE_URL_GOREST,"/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	 Assert.assertEquals(response.statusCode(), 201);
	 String userId=response.jsonPath().getString("id");
	 System.out.println("user id is==>"+ userId);
	 
	 //Get
	 Response responseGet =restclient.get(BASE_URL_GOREST,"/public/v2/users/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	 Assert.assertEquals(responseGet.getStatusCode(), 200);
	 Assert.assertEquals(responseGet.jsonPath().getString("id"), userId);
	 Assert.assertEquals(response.jsonPath().getString("name"), user.getName());
	 Assert.assertEquals(response.jsonPath().getString("email"), user.getEmail());
	 Assert.assertEquals(response.jsonPath().getString("gender"), user.getGender());
	 Assert.assertEquals(response.jsonPath().getString("status"), user.getStatus());
	 
  }	
// create user with json file
  @Test 
  public void creatUserWithJsonFile() {
	  
	  File userJsonFile=new File(".\\src\\test\\resources\\jsons\\gorestUser.json");
	 Response response =restclient.post(BASE_URL_GOREST,"/public/v2/users", userJsonFile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	 Assert.assertEquals(response.statusCode(), 201);
	 
  }
  
  
}
