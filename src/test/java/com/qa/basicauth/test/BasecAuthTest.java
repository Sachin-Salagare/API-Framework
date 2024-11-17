package com.qa.basicauth.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BasecAuthTest  extends BaseTest{
	
	@Test
	public void basicAuthTest() {
		Response response =restclient.get(BASE_URL_BASIC_AUTH, "/basic_auth", null, null, AuthType.BASIC_AUTH, ContentType.ANY);
		Assert.assertEquals(response.asString().contains("Congratulations! You must have the proper credentials."), true);
	}

}
