package com.qa.api.mocking.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockGetUserAPITest extends BaseTest {

	@Test
	public void getDummyUsersTest() {
		APIMocks.getDummyUser();
		Response response = restclient.get(BASE_URL_LOCALHOST_PORT, "/api/users", null, null, AuthType.No_AUTH,ContentType.ANY);

		Assert.assertEquals(response.statusCode(), 200);

		// 40.00 7th

	}

	@Test
	public void getDummyUsersTestWithJsonFile() {
		APIMocks.getDummyUser();
		Response response = restclient.get(BASE_URL_LOCALHOST_PORT, "/api/users", null, null, AuthType.No_AUTH,ContentType.ANY);
		String name = response.jsonPath().getString("name");// .then().assertThat().statusCode(200).and().body("name",
															// equalTo("Sachin123"));
		Assert.assertEquals(name, "Sachin123");
	}
	
	@Test
	public void getDummyUsersTestWithJsonFileWithQueryParam() {
		APIMocks.getDummyUserWithQueryParam();;
		Map<String,String> map=Map.of("name","Sachin123");
		Response response = restclient.get(BASE_URL_LOCALHOST_PORT, "/api/users", map, null, AuthType.No_AUTH,ContentType.ANY);
		String name = response.jsonPath().getString("name");// .then().assertThat().statusCode(200).and().body("name",
															// equalTo("Sachin123"));
		Assert.assertEquals(name, "Sachin123");
	}	
	

}
