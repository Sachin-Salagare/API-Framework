package com.qa.products.api.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductApiTest extends BaseTest {
	
	
	@Test
	public void getAllProducts() {
		Response response=restclient.get(BASE_URL_PRODUCT,"/products", null, null, AuthType.No_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test
	public void getAllProductsLimitTest() {
		
		Map<String,String> quaryParam=Map.of("limit","5");
		Response response=restclient.get(BASE_URL_PRODUCT,"/products", quaryParam, null, AuthType.No_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}	

}
