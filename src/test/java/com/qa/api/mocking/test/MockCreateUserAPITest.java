package com.qa.api.mocking.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class MockCreateUserAPITest extends BaseTest {
	
	@Test
	public void crateDummyUserTest() {
		APIMocks.createDummyUser();
		String dummyJson="{\"name\":\"jhon\"}";
		Response response= restclient.post(BASE_URL_LOCALHOST_PORT, "/api/users", dummyJson, null, null, AuthType.No_AUTH, ContentType.JSON);
		response.then()
		.assertThat()
		.statusCode(201)
		.statusLine(equalTo("HTTP/1.1 201 user is created"))
		.body("id", equalTo("1"));
		
		Assert.assertEquals(response.statusCode(), 201);
		
				
	}
	
	@Test
	public void crateDummyUserTestWithJsonFile() {
		APIMocks.createDummyUserWithJsonFile();
		
		String dummyJson="{\"name\":\"jhon\"}";
		Response response= restclient.post(BASE_URL_LOCALHOST_PORT, "/api/users", dummyJson, null, null, AuthType.No_AUTH, ContentType.JSON);
		response.then()
		.assertThat()
		.statusCode(201)
		.statusLine(equalTo("HTTP/1.1 201 user is created"))
		.body("name", equalTo("Sachin123"));
		
		Assert.assertEquals(response.statusCode(), 201);	
	}
}
