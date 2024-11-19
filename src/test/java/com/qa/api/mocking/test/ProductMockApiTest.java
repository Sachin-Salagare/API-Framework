package com.qa.api.mocking.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductMockApiTest extends BaseTest {

	
	@Test
	public void getDummyProductTestWithJsonFile() {
		APIMocks.getDummyProductWithJsonFile();
		Response response =restclient.get(BASE_URL_LOCALHOST_PORT, "/api/products", null, null, AuthType.No_AUTH, ContentType.ANY);
	    Assert.assertEquals(response.statusCode(), 200);
	}
}
