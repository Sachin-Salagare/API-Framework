package com.qa.reqres.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqResTest extends BaseTest {
 
	@Test
	public void getSingleUserTest() {
	 Response response=restclient.get(BASE_URL_REQ_RES ,"/api/users?page=2", null, null, AuthType.No_AUTH, ContentType.JSON);
	 Assert.assertEquals(response.statusCode(), 200);
	}
	
	
}
